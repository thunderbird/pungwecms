package com.pungwe.cms.jpa.entity;

import com.pungwe.cms.core.entity.EntityInstance;
import com.pungwe.cms.jpa.converter.HashMapBinaryJSONConverter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by ian on 05/12/2015.
 */
@Entity
@Table(name = "entity_instance")
public class EntityInstanceImpl implements EntityInstance<EntityInstanceIdImpl> {

	protected EntityInstanceIdImpl id;
	protected Date dateCreated;
	protected Date dateModified;
	protected Map<String, ?> attributes;
	protected Map<String, ?> data;

	@Override
	@EmbeddedId
	public EntityInstanceIdImpl getId() {
		return id;
	}

	@Override
	public void setId(EntityInstanceIdImpl id) {
		this.id = id;
	}

	@Override
	@Column(name = "date_created")
	@CreatedDate
	public Date getDateCreated() {
		return dateCreated != null ? (Date)dateCreated : null;
	}

	@Override
	public void setDateCreated(Date date) {
		this.dateCreated = date != null ? (Date)date.clone() : null;
	}

	@Override
	@Column(name = "date_modified")
	@LastModifiedDate
	public Date getDateModified() {
		return dateModified != null ? (Date)dateModified.clone() : null;
	}

	@Override
	public void setDateModified(Date date) {
		this.dateModified = date != null ? (Date)date.clone() : null;
	}

	@Override
	@Column(name = "attributes")
	@Convert(converter = HashMapBinaryJSONConverter.class)
	public Map<String, ?> getAttributes() {
		if (attributes == null) {
			this.attributes = new HashMap<>();
		}
		return attributes;
	}

	@Override
	public void setAttributes(Map<String, ?> attribute) {
		this.attributes = attribute;
	}

	@Override
	@Column(name = "data")
	@Convert(converter = HashMapBinaryJSONConverter.class)
	public Map<String, ?> getData() {
		if (data == null) {
			return new HashMap<>();
		}
		return data;
	}

	@Override
	public void setData(Map<String, ?> data) {
		this.data = data;
	}
}
