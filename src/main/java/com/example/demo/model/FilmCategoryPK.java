package com.example.demo.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the film_category database table.
 * 
 */
@Embeddable
public class FilmCategoryPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="film_id", columnDefinition = "smallint(5)")
	private Long filmId;

	@Column(name="category_id", columnDefinition = "smallint(5)")
	private byte categoryId;

	public FilmCategoryPK() {
	}
	public Long getFilmId() {
		return this.filmId;
	}
	public void setFilmId(Long filmId) {
		this.filmId = filmId;
	}
	public byte getCategoryId() {
		return this.categoryId;
	}
	public void setCategoryId(byte categoryId) {
		this.categoryId = categoryId;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof FilmCategoryPK)) {
			return false;
		}
		FilmCategoryPK castOther = (FilmCategoryPK)other;
		return 
			(this.filmId == castOther.filmId)
			&& (this.categoryId == castOther.categoryId);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.filmId.intValue();
		hash = hash * prime + ((int) this.categoryId);
		
		return hash;
	}
}