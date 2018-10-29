package org.colorcoding.ibas.bobas.organization.post;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import org.colorcoding.ibas.bobas.organization.IUser;
import org.colorcoding.ibas.initialfantasy.bo.shell.User;

@XmlAccessorType(XmlAccessType.NONE)
@XmlType(name = "Organization")
@XmlRootElement(name = "Organization")
public class Organization {
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Organization) {
			Organization org = (Organization) obj;
			if (this.getCode().equals(org.getCode())) {
				return true;
			}
		}
		return super.equals(obj);
	}

	@XmlElement(name = "code")
	private String code;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@XmlElementWrapper(name = "organizations")
	@XmlElement(name = "organization", type = Organization.class)
	private List<Organization> organizations;

	public List<Organization> getOrganizations() {
		if (organizations == null) {
			organizations = new ArrayList<>();
		}
		return organizations;
	}

	public void setOrganizations(List<Organization> organizations) {
		this.organizations = organizations;
	}

	@XmlElement(name = "manager", type = User.class)
	private IUser manager;

	public IUser getManager() {
		return manager;
	}

	public void setManager(IUser manager) {
		this.manager = manager;
	}

	@XmlElementWrapper(name = "members")
	@XmlElement(name = "member", type = User.class)
	private List<IUser> members;

	public List<IUser> getMembers() {
		if (members == null) {
			members = new ArrayList<>();
		}
		return members;
	}

	public void setMembers(List<IUser> members) {
		this.members = members;
	}

	@Override
	public String toString() {
		return String.format("{Org [%s] manager [%s]}", this.getCode(), this.getManager());
	}
}
