package com.pungwe.cms.core.block.impl;

import com.pungwe.cms.core.block.BlockConfig;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by ian on 07/03/2016.
 */
public class BlockConfigImpl implements BlockConfig<String, BlockConfigImpl> {

	String id;
	String name;
    String adminTitle;
    String description;
	String theme;
	String region;
	String context;
	int weight;
	Map<String, Object> settings = new LinkedHashMap<>();

	@Override
	public String getId() {
		return id;
	}

	@Override
	public void setId(String id) {
		this.id = id;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

    @Override
    public String getAdminTitle() {
        return adminTitle;
    }

    @Override
    public void setAdminTitle(String adminTitle) {
        this.adminTitle = adminTitle;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public void setDescription(String description) {
        this.description = description;
    }

    @Override
	public String getTheme() {
		return theme;
	}

	@Override
	public void setTheme(String theme) {
		this.theme = theme;
	}

	@Override
	public String getRegion() {
		return region;
	}

	@Override
	public void setRegion(String region) {
		this.region = region;
	}

	@Override
	public String getContext() {
		return context;
	}

	@Override
	public void setContext(String context) {
		this.context = context;
	}

	@Override
	public int getWeight() {
		return weight;
	}

	@Override
	public void setWeight(int weight) {
		this.weight = weight;
	}

	@Override
	public Map<String, Object> getSettings() {
		return settings;
	}

	@Override
	public void setSettings(Map<String, Object> settings) {
		this.settings = settings;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		BlockConfigImpl that = (BlockConfigImpl) o;

		return id.equals(that.id);

	}

	@Override
	public int hashCode() {
		return id.hashCode();
	}
}
