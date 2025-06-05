package com.epicode.U5D2;

import com.epicode.U5D2.entities.*;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

@SpringBootTest
class Esercizio30102023ApplicationTests {

	private static AnnotationConfigApplicationContext ctx;

	@BeforeAll
	public static void init() {
		ctx = new AnnotationConfigApplicationContext(U5D2Application.class);
	}

	@AfterAll
	public static void close() {
		ctx.close();
	}

	@Test
	void contextLoads() {
	}

	@Test
	public void verificaPrezzoMargherita(){
		Pizza margherita = ctx.getBean("pizza_margherita", Pizza.class);
		Assertions.assertEquals(700 + 0 + 92, margherita.getCalories());
	}

	@Test
	public void verificaTotaleOrdine(){
		Table t1 = ctx.getBean("Tavolo1", Table.class);

		Order o1 = new Order(2, t1);
		o1.addItem(ctx.getBean("lemonade", Drink.class));
		o1.addItem(ctx.getBean("lemonade", Drink.class));
		o1.addItem(ctx.getBean("pizza_margherita", Pizza.class));
		o1.addItem(ctx.getBean("salami_pizza", Pizza.class));

		double expected =
				ctx.getBean("lemonade", Drink.class).getPrice() * 2 +
						ctx.getBean("pizza_margherita", Pizza.class).getPrice() +
						ctx.getBean("salami_pizza", Pizza.class).getPrice() +
						t1.getCostoCoperto() * 2;

		Assertions.assertEquals(expected, o1.getTotal(), 0.01);
	}

	@Test
	public void verificaNumProdottiMenu(){
		Menu menu = ctx.getBean(Menu.class);
		int totalItems =
				menu.getPizzaList().size() +
						menu.getDrinkList().size() +
						menu.getToppingList().size();

		Assertions.assertEquals(4 + 3 + 5, totalItems);
	}

	@ParameterizedTest
	@CsvSource({"Tavolo1,1.5", "Tavolo2,1.5", "Tavolo3,1.5"})
	public void verificaCostoCopertoTavoli(String tavolo, double costoCoperto){
		Table t = ctx.getBean(tavolo, Table.class);
		Assertions.assertEquals(costoCoperto, t.getCostoCoperto(), 0.01);
	}

	@ParameterizedTest
	@CsvSource({"lemonade,1.29", "water,1.29", "wine,7.49"})
	public void verificaPrezziDrink(String nomeDrink, double prezzo){
		Drink drink = ctx.getBean(nomeDrink, Drink.class);
		Assertions.assertEquals(prezzo, drink.getPrice(), 0.01);
	}
}
