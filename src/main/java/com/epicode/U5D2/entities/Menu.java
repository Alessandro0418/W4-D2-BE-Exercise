package com.epicode.U5D2.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import org.springframework.stereotype.Component;

import java.util.List;

@Getter
@ToString
@AllArgsConstructor
@Component
public class Menu {
	private List<Pizza> pizzaList;
	private List<Drink> drinkList;
	private List<Topping> toppingList;

	public void printMenu() {
		System.out.println("******* Menu *******");
		System.out.println("PIZZAS");
		this.pizzaList.forEach(System.out::println);
		System.out.println();

		System.out.println("TOPPINGS");
		this.toppingList.forEach(System.out::println);
		System.out.println();

		System.out.println("DRINKS");
		this.drinkList.forEach(System.out::println);
		System.out.println();

	}
}
