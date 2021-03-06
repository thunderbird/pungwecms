package com.pungwe.cms.core.menu.impl;

import com.pungwe.cms.core.menu.MenuConfig;
import com.pungwe.cms.core.menu.services.MenuConfigService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.stream.Collectors;


@Service
public class MenuConfigServiceImpl implements MenuConfigService<MenuConfigImpl> {

	public List<MenuConfigImpl> menuItems = new LinkedList<>();

	@Override
	public MenuConfigImpl newInstance() {
		return new MenuConfigImpl();
	}

	@Override
	public MenuConfigImpl newInstance(String menu, String parent, String name, String title, String description, boolean external, String target, String url, int weight, boolean pattern, boolean task) {
		MenuConfigImpl config = newInstance();
        StringBuilder id = new StringBuilder().append(menu).append(".");
        if (org.apache.commons.lang3.StringUtils.isNotBlank(parent)) {
            id.append(parent).append(".");
        }
        id.append(name);
        config.setId(id.toString());
		config.setMenu(menu);
		config.setParent(parent);
		config.setName(name);
		config.setTitle(title);
		config.setDescription(description);
		config.setExternal(external);
		config.setTarget(target);
		config.setUrl(url);
		config.setWeight(weight);
        config.setTask(task);

		StringBuilder menuPath = new StringBuilder();
		if (!StringUtils.isEmpty(parent)) {
			menuPath.append(parent).append(".");
		}
		menuPath.append(name);
		config.setPath(menuPath.toString());
        config.setPattern(pattern);
		return config;
	}

	@Override
	public void saveMenuItem(MenuConfig... menuItems) {
		assert menuItems != null && menuItems.length > 0;
		saveMenuItem(Arrays.asList(menuItems));
	}

	@Override
	public void saveMenuItem(Collection<MenuConfig> menuItems) {
		assert menuItems != null && menuItems.iterator().hasNext();
		// Stream and cast, exception will be thrown if there is anything dodgy!
		this.menuItems.addAll(menuItems.stream().map(m -> (MenuConfigImpl)m).collect(Collectors.toList()));
	}

	@Override
	public List<MenuConfigImpl> menuTreeForUrl(String menu, String url) {

		List<MenuConfigImpl> tree = new LinkedList<>();

		final Optional<MenuConfigImpl> leaf = menuItems.stream().filter(menuConfig -> menuConfig.getUrl().equals(url) && menuConfig.getMenu().equals(menu)).findFirst();
		if (leaf.isPresent()) {
			tree.add(leaf.get());
		} else {
			return tree;
		}

		Optional<MenuConfigImpl> parent = menuItems.stream().filter(menuConfig -> menuConfig.getMenu().equals(menu) && menuConfig.getName().equals(leaf.get().getParent())).findFirst();

		while (parent.isPresent()) {
			tree.add(parent.get());
			final String parentPath = parent.get().getParent();
			parent = menuItems.stream().filter(menuConfig -> menuConfig.getMenu().equals(menu) && menuConfig.getPath().equals(parentPath)).findFirst();
		}
		Collections.reverse(tree);
		return tree;
	}

	@Override
	public List<MenuConfigImpl> getTopLevelMenuItems(String menu) {
		return menuItems.stream().filter(menuConfig -> StringUtils.isEmpty(menuConfig.getParent()) && menuConfig.getMenu().equals(menu)).collect(Collectors.toList());
	}

	@Override
	public List<MenuConfigImpl> getMenuItems(String menu, String parent) {
		return menuItems.stream().filter(menuConfig -> menuConfig.getMenu().equals(menu) && menuConfig.getParent().equals(parent)).collect(Collectors.toList());
	}

	@Override
	public List<MenuConfig> getMenuItemsByParent(String menu, String path, boolean task) {
		return getMenuItems(menu, path).stream().filter(menuConfig -> menuConfig.isTask()).collect(Collectors.toList());
	}
}
