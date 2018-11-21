package com.banking.entity;

import javax.persistence.*;

@MappedSuperclass
@Access(AccessType.FIELD)
public class BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected Integer id;

	public BaseEntity() {
	}

	public BaseEntity(Integer id) {
		this.id = id;
	}

	public boolean isNew() {
		return this.id == null;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		BaseEntity that = (BaseEntity) o;
		if (id == null || that.id == null) {
			throw new IllegalArgumentException("Equals '" + this + "' and '" + that + "' with null id");
		}
		return id.equals(that.id);
	}

	@Override
	public int hashCode() {
		return (id == null) ? 0 : id;
	}
}
