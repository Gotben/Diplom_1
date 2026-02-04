package practikum;

import org.junit.Before;
import org.junit.Test;
import praktikum.Bun;
import praktikum.Burger;
import praktikum.Ingredient;
import praktikum.IngredientType;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class BurgerTest {

    private Burger burger;
    private Bun bun;
    private Ingredient ingredient;

    @Before
    public void setUp() {
        burger = new Burger();

        bun = mock(Bun.class);
        when(bun.getPrice()).thenReturn(50f);
        when(bun.getName()).thenReturn("Булка");

        ingredient = mock(Ingredient.class);
        when(ingredient.getPrice()).thenReturn(20f);
        when(ingredient.getType()).thenReturn(IngredientType.FILLING);
        when(ingredient.getName()).thenReturn("Начинка");

        burger.setBuns(bun);
    }

    @Test
    public void setBunsSetsCorrectBun() {
        assertEquals(bun, burger.getBuns());
    }

    @Test
    public void addIngredientIncreasesPrice() {
        burger.addIngredient(ingredient);
        assertEquals(120f, burger.getPrice(), 0.001);
    }

    @Test
    public void removeIngredientDecreasesPrice() {
        burger.addIngredient(ingredient);
        burger.removeIngredient(0);
        assertEquals(100f, burger.getPrice(), 0.001);
    }

    @Test
    public void moveIngredientDoesNotBreakPrice() {
        Ingredient second = mock(Ingredient.class);
        when(second.getPrice()).thenReturn(10f);
        when(second.getType()).thenReturn(IngredientType.SAUCE);
        when(second.getName()).thenReturn("Соус");

        burger.addIngredient(ingredient);
        burger.addIngredient(second);

        burger.moveIngredient(0, 1);

        assertEquals(130f, burger.getPrice(), 0.001);
    }
}
