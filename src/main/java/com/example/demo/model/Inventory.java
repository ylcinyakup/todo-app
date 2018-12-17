package com.example.demo.model;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.util.Date;
import java.util.List;


/**
 * The persistent class for the inventory database table.
 * 
 */
@Entity
@NamedQuery(name="Inventory.findAll", query="SELECT ı FROM Inventory ı")
public class Inventory implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="inventory_id", columnDefinition = "mediumint(8)")
	private Long inventoryId;

	@Column(name="last_update")
	private Date lastUpdate;

	//bi-directional many-to-one association to Film
	@JsonBackReference
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="film_id")
	private Film film;

	//bi-directional many-to-one association to Store
	@JsonBackReference
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="store_id")
	private Store store;

	//bi-directional many-to-one association to Rental
	@JsonManagedReference
	@OneToMany(mappedBy="inventory")
	private List<Rental> rentals;

	public Inventory() {
	}

	public Long getInventoryId() {
		return this.inventoryId;
	}

	public void setInventoryId(Long inventoryId) {
		this.inventoryId = inventoryId;
	}

	public Date getLastUpdate() {
		return this.lastUpdate;
	}

	public void setLastUpdate(Date lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public Film getFilm() {
		return this.film;
	}

	public void setFilm(Film film) {
		this.film = film;
	}

	public Store getStore() {
		return this.store;
	}

	public void setStore(Store store) {
		this.store = store;
	}

	public List<Rental> getRentals() {
		return this.rentals;
	}

	public void setRentals(List<Rental> rentals) {
		this.rentals = rentals;
	}

	public Rental addRental(Rental rental) {
		getRentals().add(rental);
		rental.setInventory(this);

		return rental;
	}

	public Rental removeRental(Rental rental) {
		getRentals().remove(rental);
		rental.setInventory(null);

		return rental;
	}

}