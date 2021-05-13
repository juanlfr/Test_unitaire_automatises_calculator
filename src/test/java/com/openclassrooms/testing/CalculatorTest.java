package com.openclassrooms.testing;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.text.MessageFormat;
import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

class CalculatorTest {

	private Calculator calculatorUnderTest;
	private static Instant startedAt;

	@BeforeAll
	public static void initStartingTime() {
		System.out.println("Appel avant tous les tests");
		startedAt = Instant.now();
	}

	@AfterAll
	public static void showTestDuration() {
		System.out.println("Appel apres tous les test");
		Instant endedAt = Instant.now();
		long duration = Duration.between(startedAt, endedAt).toMillis();
		System.out.println(MessageFormat.format("Duree des test {0} ms", duration));

	}

	@BeforeEach
	public void initCalculator() {
		calculatorUnderTest = new Calculator();
		System.out.println("Appel avant chaque test");
	}

	@AfterEach
	public void undefCalculator() {
		System.out.println("Appel apres chaque test");
		calculatorUnderTest = null;
	}

	@Test
	void testAddTwoPositiveNumbers() {
		// Arrange
		int a = 2;
		int b = 3;
		// Calculator calculator = new Calculator(); => plu besoin il y a le BeforeEach
		// Act
		int somme = calculatorUnderTest.add(a, b);
		// Assert avec Junit
		assertEquals(5, somme);
		// Avec Assertionsj
		assertThat(somme).isEqualTo(5);
	}

	@Test
	void testMultiplyNumbers() {
		// Arrange
		int a = 2;
		int b = 3;
		// Calculator calculator = new Calculator();
		// Act
		int product = calculatorUnderTest.multiply(a, b);
		// Assert
		assertEquals(6, product);
		// Avec Assertionsj
		assertThat(product).isEqualTo(6);
	}

	@ParameterizedTest(name = "{0} x 0 doit etre egal a 0")
	@ValueSource(ints = { 1, 2, 42, 1001, 5089 })
	public void multiplyShouldReturnZero(int arg) {
		System.out.println("@ParameterizedTestJuan");
		// Arrange
		// tout est pret
		// Act
		final int actualresult = calculatorUnderTest.multiply(arg, 0);
		// Assert
		assertEquals(0, actualresult);
	}

	@ParameterizedTest(name = "{0} + {1} doit etre égal a {2} ")
	@CsvSource({ "1,2,3", "2,2,4", "4,5,9" })
	public void addTwoValuesWithResult(int arg1, int arg2, int expectedResult) {
		// Arrange
		// tout est pret
		// Act
		int actualresult = calculatorUnderTest.add(arg1, arg2);
		// Assert
		assertEquals(expectedResult, actualresult);
	}

	@Timeout(1)
	@Test
	public void longCalculShouldComputeLessThan1Second() {
		// Act7
		calculatorUnderTest.longCalcul();

	}

	@Test
	public void listDigitsShouldRetournTheListOfDigits() {
		// GIVEN
		int number = 9587;
		// WHEN
		List<Integer> actualDigits = calculatorUnderTest.digitsSet(number);
		// THEN
		List<Integer> expectedDigits = Stream.of(5, 7, 8, 9).collect(Collectors.toList());
		// assertEquals(expectedDigits, actualDigits);// JUnit
		assertThat(actualDigits).containsExactlyInAnyOrder(5, 8, 9, 7);// AssertJ

	}

}
