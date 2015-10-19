package net.xicp.zyl_me.continuous.reading.dal.entity;

import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(catalog="continuousreading",name="url")
public class Url {
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Id
	private String id;
	private String name;
	@ManyToOne
	@JoinColumn(name="userid")
	private User user;
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String mame) {
		this.name = mame;
	}
}
