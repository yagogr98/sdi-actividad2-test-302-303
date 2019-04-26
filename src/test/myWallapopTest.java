package test.java.com.uniovi.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
//Ordenamos las pruebas por el nombre del meÌ�todo

import test.java.com.uniovi.tests.pageobjects.PO_AddOffer;
import test.java.com.uniovi.tests.pageobjects.PO_HomeView;
import test.java.com.uniovi.tests.pageobjects.PO_LoginView;
import test.java.com.uniovi.tests.pageobjects.PO_PrivateView;
import test.java.com.uniovi.tests.pageobjects.PO_Register;
import test.java.com.uniovi.tests.pageobjects.PO_View;
import test.java.com.uniovi.tests.util.SeleniumUtils;;


@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class myWallapopTest {
	// En Windows (Debe ser la versioÌ�n 65.0.1 y desactivar las actualizacioens
	// automaÌ�ticas)):
	static String PathFirefox64 = "C:\\Program Files\\Mozilla Firefox\\firefox.exe";
	static String Geckdriver022 = "C:\\path\\geckodriver024win64.exe";
	// En MACOSX (Debe ser la versioÌ�n 65.0.1 y desactivar las actualizacioens
	// automaÌ�ticas):
	//	static String PathFirefox65 = "/Applications/Firefox.app/Contents/MacOS/firefox-bin";
	//	static String Geckdriver024 = "/Users/yagogarciarodriguez/Downloads/a/geckodriver024mac";
	// ComuÌ�n a Windows y a MACOSX
	static WebDriver driver = getDriver(PathFirefox64, Geckdriver022);
	static String URLlocal = "http://localhost:8090";
	static String URLremota = "http://ec2-34-224-173-6.compute-1.amazonaws.com:8080";
	static String URL = URLremota; // Se va a probar con la URL remota, sino URL=URLlocal

	public static WebDriver getDriver(String PathFirefox, String Geckdriver) {

		System.setProperty("webdriver.firefox.bin", PathFirefox);
		System.setProperty("webdriver.gecko.driver", Geckdriver);

		WebDriver driver = new FirefoxDriver();

		return driver;
	}
	// Antes de cada prueba se navega al URL home de la aplicacioÌ�nn

	@Before
	public void setUp() {
		driver.navigate().to(URL);
	}

	// DespueÌ�s de cada prueba se borran las cookies del navegador
	@After
	public void tearDown() {
		driver.manage().deleteAllCookies();
	}

	// Antes de la primera prueba
	@BeforeClass
	static public void begin() {
		// PO_NavView.changeIdiom(driver, "btnSpanish");
	}

	// [Prueba1] Registro de Usuario con datos vÃ¡lidos.
	@Test
	public void PR01() {
		// Vamos al formulario de logueo.
		PO_HomeView.clickOption(driver, "signup", "class", "btn btn-primary");
		// Rellenamos el formulario
		PO_Register.fillForm(driver, "123@gmail.com", "12345", "12345", "12345", "12345");
		// Comprobamos pagina de inicio
		PO_View.checkElement(driver, "text", "myWallapop");
	}

	// [Prueba2] Registro de Usuario con datos invÃ¡lidos (email vacÃ­o, nombre vacÃ­o,
	// apellidos vacÃ­os).

	@Test
	public void PR02() {
		// Vamos al formulario de logueo.
		PO_HomeView.clickOption(driver, "signup", "class", "btn btn-primary");
		// Rellenamos el formulario
		PO_Register.fillForm(driver, "", "", "", "12345", "12345");
		// Miramos que seguimos en la misma pagina
		PO_View.checkElement(driver, "text", "Email");
	}

	// [Prueba3] Registro de Usuario con datos invÃ¡lidos (repeticiÃ³n de contraseÃ±a
	// invÃ¡lida).
	@Test
	public void PR03() {// Vamos al formulario de logueo.
		PO_HomeView.clickOption(driver, "signup", "class", "btn btn-primary");
		// Rellenamos el formulario
		PO_Register.fillForm(driver, "123@gmail.com", "12345", "12345", "12345", "54321");
		// Miramos que seguimos en la misma pagina
		PO_View.checkElement(driver, "text", "Email");
	}

	// [Prueba4] Registro de Usuario con datos invÃ¡lidos (email existente).
	@Test
	public void PR04() {
		PO_HomeView.clickOption(driver, "signup", "class", "btn btn-primary");
		// Rellenamos el formulario
		PO_Register.fillForm(driver, "pruebadeemail@prueba.com", "12345", "12345", "12345", "12345");
		// Miramos que seguimos en la misma pagina
		PO_View.checkElement(driver, "text", "Email");
	}

	// [Prueba5] Inicio de sesiÃ³n con datos vÃ¡lidos (administrador).
	@Test
	public void PR05() {
		// Vamos al formulario de logueo.
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		// Rellenamos el formulario
		PO_LoginView.fillForm(driver, "admin@email.com", "admin");
		// COmprobamos que entramos en la pagina de inicio
		PO_View.checkElement(driver, "text", "admin@email.com");
	}

	// [Prueba6] Inicio de sesiÃ³n con datos vÃ¡lidos (usuario estÃ¡ndar).
	@Test
	public void PR06() {
		// Vamos al formulario de logueo.
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		// Rellenamos el formulario
		PO_LoginView.fillForm(driver, "pruebadeemail@prueba.com", "123456");
		// COmprobamos que entramos en la pagina de inicio
		PO_View.checkElement(driver, "text", "pruebadeemail@prueba.com");
	}

	// [Prueba7] Inicio de sesiÃ³n con datos invÃ¡lidos (usuario estÃ¡ndar, campo email
	// y contraseÃ±a vacÃ­os).
	@Test
	public void PR07() {
		// Vamos al formulario de logueo.
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		// Rellenamos el formulario
		PO_LoginView.fillForm(driver, "", "");
		// COmprobamos que estamos en la pagina de login
		PO_View.checkElement(driver, "text", "Email");
	}

	// [Prueba8] Inicio de sesiÃ³n con datos vÃ¡lidos (usuario estÃ¡ndar, email
	// existente, pero contraseÃ±a
	// incorrecta).
	@Test
	public void PR08() {
		// Vamos al formulario de logueo.
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		// Rellenamos el formulario
		PO_LoginView.fillForm(driver, "pruebadeemail@prueba.com", "");
		// COmprobamos que estamos en la pagina de login
		PO_View.checkElement(driver, "text", "Email");

	}

	// [Prueba9] Inicio de sesiÃ³n con datos invÃ¡lidos (usuario estÃ¡ndar, email no
	// existente en la aplicaciÃ³n).
	@Test
	public void PR09() {
		// Vamos al formulario de logueo.
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		// Rellenamos el formulario
		PO_LoginView.fillForm(driver, "emailinventado@l.com", "123456");
		// COmprobamos que estamos en la pagina de login
		PO_View.checkElement(driver, "text", "Email");

	}

	// [Prueba10] Hacer click en la opciÃ³n de salir de sesiÃ³n y comprobar que se
	// redirige a la pÃ¡gina de inicio
	// de sesiÃ³n (Login).
	@Test
	public void PR10() {
		// Vamos al formulario de logueo.
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		// Rellenamos el formulario
		PO_LoginView.fillForm(driver, "pruebadeemail@prueba.com", "123456");
		// Ahora nos desconectamos
		PO_HomeView.clickOption(driver, "logout", "class", "btn btn-primary");
		// COmprobamos que entramos en la pagina de login
		PO_View.checkElement(driver, "text", "Email");

	}
	// [Prueba11] Comprobar que el botÃ³n cerrar sesiÃ³n no estÃ¡ visible si el usuario
	// no estÃ¡ autenticado.

	@Test
	public void PR11() {
		// Vamos al formulario de logueo.
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		// Rellenamos el formulario
		PO_LoginView.fillForm(driver, "prueba@prueba.com", "123456");
		// Ahora nos desconectamos
		PO_HomeView.clickOption(driver, "logout", "class", "btn btn-primary");
		// COmprobamos que entramos en la pagina de login
		PO_View.checkElement(driver, "text", "Email");

		SeleniumUtils.EsperaCargaPaginaNoTexto(driver, "Logout", PO_View.getTimeout());
	}

	// [Prueba12] Mostrar el listado de usuarios y comprobar que se muestran todos
	// los que existen en el
	// sistema
	@Test
	public void PR12() {
		// Vamos al formulario de logueo.
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		// Rellenamos el formulario con admin
		PO_LoginView.fillForm(driver, "admin@email.com", "admin");
		// Click en usuarios

		List<WebElement> elementos = PO_View.checkElement(driver, "free", "//li[contains(@id, 'users-menu')]/a");
		elementos.get(0).click();

		// Pinchamos en la opcioÌ�n de lista de usuarios.
		elementos = PO_View.checkElement(driver, "free", "//a[contains(@href,'user/list')]");
		elementos.get(0).click();
		List<WebElement> elementos1 = SeleniumUtils.EsperaCargaPagina(driver, "free", "//tbody/tr",
				PO_View.getTimeout());

		assertEquals(8, elementos1.size());
		// Salimos de sesion
		PO_PrivateView.clickOption(driver, "logout", "class", "btn btn-primary");

	}

	// [Prueba13] Ir a la lista de usuarios, borrar el primer usuario de la lista,
	// comprobar que la lista se actualiza
	// y dicho usuario desaparece.
	@Test
	public void PR13() {
		// Vamos al formulario de logueo.
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		// Rellenamos el formulario con admin
		PO_LoginView.fillForm(driver, "admin@email.com", "admin");
		// Click en usuarios

		List<WebElement> elementos = PO_View.checkElement(driver, "free", "//li[contains(@id, 'users-menu')]/a");
		elementos.get(0).click();

		// Pinchamos en la opcioÌ�n de lista de usuarios.
		elementos = PO_View.checkElement(driver, "free", "//a[contains(@href,'user/list')]");
		elementos.get(0).click();
		elementos = PO_View.checkElement(driver, "free",
				"//td[contains(text(), 'Pedro')]/following-sibling::*/input[contains(@type, 'checkbox')]");
		elementos.get(0).click();
		By boton = By.className("delete");
		driver.findElement(boton).click();
		elementos = SeleniumUtils.EsperaCargaPagina(driver, "free", "//tbody/tr", PO_View.getTimeout());
		SeleniumUtils.EsperaCargaPaginaNoTexto(driver, "Pedro", PO_View.getTimeout());
		// Salimos de sesion
		PO_PrivateView.clickOption(driver, "logout", "class", "btn btn-primary");


	}

	// [Prueba14] Ir a la lista de usuarios, borrar el Ãºltimo usuario de la lista,
	// comprobar que la lista se actualiza
	// y dicho usuario desaparece.
	@Test
	public void PR14() {
		// Vamos al formulario de logueo.
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		// Rellenamos el formulario con admin
		PO_LoginView.fillForm(driver, "admin@email.com", "admin");
		// Click en usuarios

		List<WebElement> elementos = PO_View.checkElement(driver, "free", "//li[contains(@id, 'users-menu')]/a");
		elementos.get(0).click();

		// Pinchamos en la opcioÌ�n de lista de usuarios.
		elementos = PO_View.checkElement(driver, "free", "//a[contains(@href,'user/list')]");
		elementos.get(0).click();
		elementos = PO_View.checkElement(driver, "free",
				"//td[contains(text(), '123@gmail.com')]/following-sibling::*/input[contains(@type, 'checkbox')]");
		elementos.get(0).click();
		By boton = By.className("delete");
		driver.findElement(boton).click();

		elementos = SeleniumUtils.EsperaCargaPagina(driver, "free", "//tbody/tr", PO_View.getTimeout());
		System.out.print(elementos.size());
		SeleniumUtils.EsperaCargaPaginaNoTexto(driver, "123@gmail.com", PO_View.getTimeout());
		// Salimos de sesion
		PO_PrivateView.clickOption(driver, "logout", "class", "btn btn-primary");

	}

	// [Prueba15] Ir a la lista de usuarios, borrar 3 usuarios, comprobar que la
	// lista se actualiza y dichos
	// usuarios desaparecen.
	@Test
	public void PR15() {
		// Vamos al formulario de logueo.
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		// Rellenamos el formulario con admin
		PO_LoginView.fillForm(driver, "admin@email.com", "admin");
		// Click en usuarios

		List<WebElement> elementos = PO_View.checkElement(driver, "free", "//li[contains(@id, 'users-menu')]/a");
		elementos.get(0).click();

		// Pinchamos en la opcioÌ�n de lista de usuarios.
		elementos = PO_View.checkElement(driver, "free", "//a[contains(@href,'user/list')]");
		elementos.get(0).click();
		elementos = PO_View.checkElement(driver, "free",
				"//td[contains(text(), '789@prueba.com')]/following-sibling::*/input[contains(@type, 'checkbox')]");
		elementos.get(0).click();
		elementos = PO_View.checkElement(driver, "free",
				"//td[contains(text(), '456@prueba.com')]/following-sibling::*/input[contains(@type, 'checkbox')]");
		elementos.get(0).click();
		elementos = PO_View.checkElement(driver, "free",
				"//td[contains(text(), '123444@prueba.com')]/following-sibling::*/input[contains(@type, 'checkbox')]");
		elementos.get(0).click();
		By boton = By.className("delete");
		driver.findElement(boton).click();

		elementos = SeleniumUtils.EsperaCargaPagina(driver, "free", "//tbody/tr", PO_View.getTimeout());
		System.out.print(elementos.size());
		SeleniumUtils.EsperaCargaPaginaNoTexto(driver, "789@prueba.com", PO_View.getTimeout());
		SeleniumUtils.EsperaCargaPaginaNoTexto(driver, "456@prueba.com", PO_View.getTimeout());
		SeleniumUtils.EsperaCargaPaginaNoTexto(driver, "123444@prueba.com", PO_View.getTimeout());
		// Salimos de sesion
		PO_PrivateView.clickOption(driver, "logout", "class", "btn btn-primary");

	}

	// [Prueba16] Ir al formulario de alta de oferta, rellenarla con datos vÃ¡lidos y
	// pulsar el botÃ³n Submit.
	// Comprobar que la oferta sale en el listado de ofertas de dicho usuario.
	@Test
	public void PR16() {
		// Vamos al formulario de logueo.
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		// Rellenamos el formulario con usuario
		PO_LoginView.fillForm(driver, "prueba@prueba.com", "123456");
		// Click en usuarios

		List<WebElement> elementos = PO_View.checkElement(driver, "free", "//li[contains(@id, 'offers-menu')]/a");
		elementos.get(0).click();

		// Pinchamos en la opcioÌ�n de aÃ±adir oferta y aÃ±adimos.
		elementos = PO_View.checkElement(driver, "free", "//a[contains(@href,'offer/add')]");
		elementos.get(0).click();
		PO_AddOffer.fillForm(driver, "titulodeofertadeprueba", "descripciondeofeertanueva", "1", "http://google.es");

		// Comprobamos que sale
		elementos = PO_View.checkElement(driver, "free", "//li[contains(@id, 'offers-menu')]/a");
		elementos.get(0).click();

		// Pinchamos en la opcioÌ�n de aÃ±adir oferta y aÃ±adimos.
		elementos = PO_View.checkElement(driver, "free", "//a[contains(@href,'offer/mylist')]");
		elementos.get(0).click();
		// Volvemos a la Ãºltima pagina
		elementos = PO_View.checkElement(driver, "free", "//a[contains(@class, 'page-link')]");
		elementos.get(3).click();
		PO_View.checkElement(driver, "text", "descripciondeofeertanueva");

		// Salimos de sesion
		PO_PrivateView.clickOption(driver, "logout", "class", "btn btn-primary");

	}

	// [Prueba17] Ir al formulario de alta de oferta, rellenarla con datos invÃ¡lidos
	// (campo tÃ­tulo vacÃ­o) y pulsar
	// el botÃ³n Submit. Comprobar que se muestra el mensaje de campo obligatorio.
	@Test
	public void PR17() {
		// Vamos al formulario de logueo.
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		// Rellenamos el formulario con usuario
		PO_LoginView.fillForm(driver, "prueba@prueba.com", "123456");
		// Click en usuarios

		List<WebElement> elementos = PO_View.checkElement(driver, "free", "//li[contains(@id, 'offers-menu')]/a");
		elementos.get(0).click();

		// Pinchamos en la opcioÌ�n de aÃ±adir oferta y aÃ±adimos.
		elementos = PO_View.checkElement(driver, "free", "//a[contains(@href,'offer/add')]");
		elementos.get(0).click();
		PO_AddOffer.fillForm(driver, "", "descripciondeofeertanueva", "1", "http://google.es");
		PO_View.checkElement(driver, "text", "Titulo incorrecto");

		// Salimos de sesion
		PO_PrivateView.clickOption(driver, "logout", "class", "btn btn-primary");

	}

	// [Prueba18] Mostrar el listado de ofertas para dicho usuario y comprobar que
	// se muestran todas los que
	// existen para este usuario.
	@Test
	public void PR18() {
		// Vamos al formulario de logueo.
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		// Rellenamos el formulario con admin
		PO_LoginView.fillForm(driver, "prueba@prueba.com", "123456");
		// Click en usuarios

		List<WebElement> elementos = PO_View.checkElement(driver, "free", "//li[contains(@id, 'offers-menu')]/a");
		elementos.get(0).click();

		// Pinchamos en la opcioÌ�n de lista de usuarios.
		elementos = PO_View.checkElement(driver, "free", "//a[contains(@href,'offer/mylist')]");
		elementos.get(0).click();
		List<WebElement> elementos1 = SeleniumUtils.EsperaCargaPagina(driver, "free", "//tbody/tr",
				PO_View.getTimeout());

		assertTrue(elementos1.size() == 5);
		// Salimos de sesion
		PO_PrivateView.clickOption(driver, "logout", "class", "btn btn-primary");

	}

	// [Prueba19] Ir a la lista de ofertas, borrar la primera oferta de la lista,
	// comprobar que la lista se actualiza y
	// que la oferta desaparece.
	@Test
	public void PR19() {
		// Vamos al formulario de logueo.
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		// Rellenamos el formulario con admin
		PO_LoginView.fillForm(driver, "prueba@prueba.com", "123456");
		// Click en usuarios

		List<WebElement> elementos = PO_View.checkElement(driver, "free", "//li[contains(@id, 'offers-menu')]/a");
		elementos.get(0).click();

		// Pinchamos en la opcioÌ�n de lista de ofertas.
		elementos = PO_View.checkElement(driver, "free", "//a[contains(@href,'offer/mylist')]");
		elementos.get(0).click();
		List<WebElement> elementos1 = SeleniumUtils.EsperaCargaPagina(driver, "free", "//tbody/tr",
				PO_View.getTimeout());

		elementos = PO_View.checkElement(driver, "free",
				"//td[contains(text(), 'OPEL18')]/following-sibling::*/a[contains(@href, 'offer/delete')]");
		elementos.get(0).click();

		elementos = SeleniumUtils.EsperaCargaPagina(driver, "free", "//tbody/tr", PO_View.getTimeout());
		System.out.print(elementos.size());
		SeleniumUtils.EsperaCargaPaginaNoTexto(driver, "OPEL 18", PO_View.getTimeout());

		// Salimos de sesion
		PO_PrivateView.clickOption(driver, "logout", "class", "btn btn-primary");

	}

	// [Prueba20] Ir a la lista de ofertas, borrar la Ãºltima oferta de la lista,
	// comprobar que la lista se actualiza y
	// que la oferta desaparece.
	@Test
	public void PR20() {
		// Vamos al formulario de logueo.
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		// Rellenamos el formulario con admin
		PO_LoginView.fillForm(driver, "prueba@prueba.com", "123456");
		// Click en usuarios

		List<WebElement> elementos = PO_View.checkElement(driver, "free", "//li[contains(@id, 'offers-menu')]/a");
		elementos.get(0).click();

		// Pinchamos en la opcioÌ�n de lista de ofertas.
		elementos = PO_View.checkElement(driver, "free", "//a[contains(@href,'offer/mylist')]");
		elementos.get(0).click();
		List<WebElement> elementos1 = SeleniumUtils.EsperaCargaPagina(driver, "free", "//tbody/tr",
				PO_View.getTimeout());

		elementos = PO_View.checkElement(driver, "free",
				"//td[contains(text(), 'OPEL15')]/following-sibling::*/a[contains(@href, 'offer/delete')]");
		elementos.get(0).click();

		elementos = SeleniumUtils.EsperaCargaPagina(driver, "free", "//tbody/tr", PO_View.getTimeout());
		System.out.print(elementos.size());
		SeleniumUtils.EsperaCargaPaginaNoTexto(driver, "OPEL15", PO_View.getTimeout());

		// Salimos de sesion
		PO_PrivateView.clickOption(driver, "logout", "class", "btn btn-primary");

	}

	// Prueba21] Hacer una bÃºsqueda con el campo vacÃ­o y comprobar que se muestra la
	// pÃ¡gina que
	// corresponde con el listado de las ofertas existentes en el sistema
	@Test
	public void PR21() {
		// Vamos al formulario de logueo.
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		// Rellenamos el formulario con admin
		PO_LoginView.fillForm(driver, "prueba@prueba.com", "123456");
		// Click en usuarios

		List<WebElement> elementos = PO_View.checkElement(driver, "class", "all");
		elementos.get(0).click();

		// Pinchamos en la opcioÌ�n de lista de usuarios.
		elementos = PO_View.checkElement(driver, "free", "//a[contains(@href,'offer/list')]");
		elementos.get(0).click();

		List<WebElement> elementos1 = SeleniumUtils.EsperaCargaPagina(driver, "free", "//tbody/tr",
				PO_View.getTimeout());

		assertEquals(5, elementos1.size());
		// Salimos de sesion
		PO_PrivateView.clickOption(driver, "logout", "class", "btn btn-primary");

	}

	// [Prueba22] Hacer una bÃºsqueda escribiendo en el campo un texto que no exista
	// y comprobar que se
	// muestra la pÃ¡gina que corresponde, con la lista de ofertas vacÃ­a.
	@Test
	public void PR22() {
		// Vamos al formulario de logueo.
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		// Rellenamos el formulario con admin
		PO_LoginView.fillForm(driver, "prueba@prueba.com", "123456");
		// Click en usuarios

		List<WebElement> elementos = PO_View.checkElement(driver, "class", "all");
		elementos.get(0).click();

		// Pinchamos en la opcioÌ�n de lista de usuarios.
		elementos = PO_View.checkElement(driver, "free", "//a[contains(@href,'offer/list')]");
		elementos.get(0).click();
		WebElement tituloe = driver.findElement(By.name("searchText"));
		tituloe.click();
		tituloe.clear();
		tituloe.sendKeys("Ferrari 458");
		// Pulsamos boton buscar
		By boton = By.className("busqueda");
		driver.findElement(boton).click();

		List<WebElement> elementos1 = SeleniumUtils.EsperaCargaPagina(driver, "free", "//tbody", PO_View.getTimeout());

		// El minimo de elementos es 1.
		assertTrue(elementos1.size() == 1);
		// Salimos de sesion
		PO_PrivateView.clickOption(driver, "logout", "class", "btn btn-primary");

	}

	// Prueba23] Sobre una bÃºsqueda determinada (a elecciÃ³n de desarrollador),
	// comprar una oferta que deja
	// un saldo positivo en el contador del comprobador. Y comprobar que el contador
	// se actualiza
	// correctamente en la vista del comprador.
	@Test
	public void PR23() {
		// Vamos al formulario de logueo.
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		// Rellenamos el formulario con admin
		PO_LoginView.fillForm(driver, "prueba@prueba.com", "123456");
		// Click en usuarios

		List<WebElement> elementos = PO_View.checkElement(driver, "class", "all");
		elementos.get(0).click();

		// Pinchamos en la opcioÌ�n de lista de usuarios.
		elementos = PO_View.checkElement(driver, "free", "//a[contains(@href,'offer/list')]");
		elementos.get(0).click();
		WebElement tituloe = driver.findElement(By.name("searchText"));
		tituloe.click();
		tituloe.clear();
		tituloe.sendKeys("OPEL21");
		// Pulsamos boton buscar
		By boton = By.className("busqueda");
		driver.findElement(boton).click();

		List<WebElement> elementos1 = SeleniumUtils.EsperaCargaPagina(driver, "free", "//tbody", PO_View.getTimeout());

		boton = By.className("botoncomprar");
		driver.findElement(boton).click();

		// finalizamos compra
		boton = By.className("end");
		driver.findElement(boton).click();

		// volver a home

		elementos = PO_View.checkElement(driver, "free", "//a[contains(@href,'/home')]");
		elementos.get(0).click();

		PO_View.checkElement(driver, "text", "60.0");
		// El minimo de elementos es 1.
		assertTrue(elementos1.size() == 1);
		// Salimos de sesion
		PO_PrivateView.clickOption(driver, "logout", "class", "btn btn-primary");

	}

	// [Prueba24] Sobre una bÃºsqueda determinada (a elecciÃ³n de desarrollador),
	// comprar una oferta que deja
	// un saldo 0 en el contador del comprobador. Y comprobar que el contador se
	// actualiza correctamente en
	// la vista del comprador.
	@Test
	public void PR24() {
		// Vamos al formulario de logueo.
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		// Rellenamos el formulario con admin
		PO_LoginView.fillForm(driver, "prueba@prueba.com", "123456");
		// Click en usuarios

		List<WebElement> elementos = PO_View.checkElement(driver, "class", "all");
		elementos.get(0).click();

		// Pinchamos en la opcioÌ�n de lista de usuarios.
		elementos = PO_View.checkElement(driver, "free", "//a[contains(@href,'offer/list')]");
		elementos.get(0).click();
		WebElement tituloe = driver.findElement(By.name("searchText"));
		tituloe.click();
		tituloe.clear();
		tituloe.sendKeys("OPEL20");
		// Pulsamos boton buscar
		By boton = By.className("busqueda");
		driver.findElement(boton).click();

		List<WebElement> elementos1 = SeleniumUtils.EsperaCargaPagina(driver, "free", "//tbody", PO_View.getTimeout());

		boton = By.className("botoncomprar");
		driver.findElement(boton).click();

		// finalizamos compra
		boton = By.className("end");
		driver.findElement(boton).click();

		// volver a home

		elementos = PO_View.checkElement(driver, "free", "//a[contains(@href,'/home')]");
		elementos.get(0).click();

		PO_View.checkElement(driver, "text", "0.0");
		// El minimo de elementos es 1.
		// Salimos de sesion
		PO_PrivateView.clickOption(driver, "logout", "class", "btn btn-primary");

	}

	// [Prueba25] Sobre una bÃºsqueda determinada (a elecciÃ³n de desarrollador),
	// intentar comprar una oferta
	// que estÃ© por encima de saldo disponible del comprador. Y comprobar que se
	// muestra el mensaje de
	// saldo no suficiente.
	@Test
	public void PR25() {

		// Vamos al formulario de logueo.
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		// Rellenamos el formulario con admin
		PO_LoginView.fillForm(driver, "prueba@prueba.com", "123456");
		// Click en usuarios

		List<WebElement> elementos = PO_View.checkElement(driver, "class", "all");
		elementos.get(0).click();

		// Pinchamos en la opcioÌ�n de lista de usuarios.
		elementos = PO_View.checkElement(driver, "free", "//a[contains(@href,'offer/list')]");
		elementos.get(0).click();
		WebElement tituloe = driver.findElement(By.name("searchText"));
		tituloe.click();
		tituloe.clear();
		tituloe.sendKeys("OPEL22");
		// Pulsamos boton buscar
		By boton = By.className("busqueda");
		driver.findElement(boton).click();

		List<WebElement> elementos1 = SeleniumUtils.EsperaCargaPagina(driver, "free", "//tbody", PO_View.getTimeout());

		boton = By.className("botoncomprar");
		driver.findElement(boton).click();

		// finalizamos compra
		boton = By.className("end");
		driver.findElement(boton).click();
		// Comprobamos mensaje
		PO_View.checkElement(driver, "text", "Error");
		// Salimos de sesion
		PO_PrivateView.clickOption(driver, "logout", "class", "btn btn-primary");

	}

	// [Prueba26] Ir a la opciÃ³n de ofertas compradas del usuario y mostrar la
	// lista. Comprobar que aparecen
	// las ofertas que deben aparecer.
	@Test
	public void PR26() {

		// Vamos al formulario de logueo.
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		// Rellenamos el formulario con admin
		PO_LoginView.fillForm(driver, "prueba@prueba.com", "123456");
		// Click en usuarios

		List<WebElement> elementos = PO_View.checkElement(driver, "class", "all");
		elementos.get(0).click();

		// Pinchamos en la opcioÌ�n de lista de usuarios.
		elementos = PO_View.checkElement(driver, "free", "//a[contains(@href,'purchase/list')]");
		elementos.get(0).click();
		PO_View.checkElement(driver, "text", "OPEL21");
		PO_View.checkElement(driver, "text", "OPEL20");
		// Salimos de sesion
		PO_PrivateView.clickOption(driver, "logout", "class", "btn btn-primary");

	}

	// [Prueba27] Visualizar al menos cuatro pÃ¡ginas en EspaÃ±ol/InglÃ©s/EspaÃ±ol
	// (comprobando que algunas
	// de las etiquetas cambian al idioma correspondiente). PÃ¡gina
	// principal/Opciones Principales de
	// Usuario/Listado de Usuarios de Admin/Vista de alta de Oferta.
	@Test
	public void PR27() {
		//Login
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		PO_LoginView.fillForm(driver, "prueba@prueba.com", "123456");
		PO_View.checkElement(driver, "text", "Bienvenido");

		//Cambio a ingles
		List<WebElement> elementos = PO_View.checkElement(driver, "text", "Idioma");
		elementos.get(0).click();
		elementos = PO_View.checkElement(driver, "free", "//a[contains(@id,'btnEnglish')]");
		elementos.get(0).click();

		PO_View.checkElement(driver, "text", "Welcome");
		
		//Cambio a espaÃ±ol
		elementos = PO_View.checkElement(driver, "text", "Language");
		elementos.get(0).click();
		elementos = PO_View.checkElement(driver, "free", "//a[contains(@id,'btnSpanish')]");
		elementos.get(0).click();

		PO_View.checkElement(driver, "text", "Bienvenido");

		elementos = PO_View.checkElement(driver, "class", "all");
		elementos.get(0).click();

		driver.navigate().to(URL + "/offer/list");
		PO_View.checkElement(driver, "text", "Mas Informacion");
		elementos = PO_View.checkElement(driver, "text", "Idioma");
		elementos.get(0).click();
		elementos = PO_View.checkElement(driver, "free", "//a[contains(@id,'btnEnglish')]");
		elementos.get(0).click();

		PO_View.checkElement(driver, "text", "More");

		elementos = PO_View.checkElement(driver, "text", "Language");
		elementos.get(0).click();
		elementos = PO_View.checkElement(driver, "free", "//a[contains(@id,'btnSpanish')]");
		elementos.get(0).click();

		PO_View.checkElement(driver, "text", "Mas Informacion");
		
		driver.navigate().to(URL + "/offer/add");
		
		PO_View.checkElement(driver, "text", "AÃ±adir");
		elementos = PO_View.checkElement(driver, "text", "Idioma");
		elementos.get(0).click();
		elementos = PO_View.checkElement(driver, "free", "//a[contains(@id,'btnEnglish')]");
		elementos.get(0).click();

		PO_View.checkElement(driver, "text", "Special");

		elementos = PO_View.checkElement(driver, "text", "Language");
		elementos.get(0).click();
		elementos = PO_View.checkElement(driver, "free", "//a[contains(@id,'btnSpanish')]");
		elementos.get(0).click();

		PO_View.checkElement(driver, "text", "AÃ±adir");
		
		driver.navigate().to(URL + "/home");
		
		PO_PrivateView.clickOption(driver, "logout", "class", "btn btn-primary");

		// Rellenamos el formulario con admin
		PO_LoginView.fillForm(driver, "admin@email.com", "admin");
		
		driver.navigate().to(URL + "/user/list");
		
		// Pinchamos en la opcioÌ�n de lista de usuarios.
		PO_View.checkElement(driver, "text", "Apellidos");
		elementos = PO_View.checkElement(driver, "text", "Idioma");
		elementos.get(0).click();
		elementos = PO_View.checkElement(driver, "free", "//a[contains(@id,'btnEnglish')]");
		elementos.get(0).click();

		PO_View.checkElement(driver, "text", "Surnames");

		elementos = PO_View.checkElement(driver, "text", "Language");
		elementos.get(0).click();
		elementos = PO_View.checkElement(driver, "free", "//a[contains(@id,'btnSpanish')]");
		elementos.get(0).click();

		PO_View.checkElement(driver, "text", "Apellidos");
		// Salimos de sesion
		PO_PrivateView.clickOption(driver, "logout", "class", "btn btn-primary");
	}

	//
	// [Prueba28] Intentar acceder sin estar autenticado a la opciÃ³n de listado de
	// usuarios del administrador. Se
	// deberÃ¡ volver al formulario de login.
	@Test
	public void PR28() {
		driver.navigate().to(URL + "/user/list");
		PO_View.checkElement(driver, "text", "Email");
	}

	// [Prueba29] Intentar acceder sin estar autenticado a la opciÃ³n de listado de
	// ofertas propias de un usuario
	// estÃ¡ndar. Se deberÃ¡ volver al formulario de login.
	@Test
	public void PR29() {
		driver.navigate().to(URL + "/offer/mylist");
		PO_View.checkElement(driver, "text", "Email");
	}

	// [Prueba30] Estando autenticado como usuario estÃ¡ndar intentar acceder a la
	// opciÃ³n de listado de
	// usuarios del administrador. Se deberÃ¡ indicar un mensaje de acciÃ³n prohibida

	public void PR30() {

		// Vamos al formulario de logueo.
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		// Rellenamos el formulario con admin
		PO_LoginView.fillForm(driver, "prueba@prueba.com", "123456");
		// Click en usuarios
		driver.navigate().to(URL + "/user/list");
		PO_View.checkElement(driver, "text", "403");
		// Salimos de sesion
		PO_PrivateView.clickOption(driver, "logout", "class", "btn btn-primary");


	}

	//
	// [Prueba31] Sobre una bÃºsqueda determinada de ofertas (a elecciÃ³n de
	// desarrollador), enviar un mensaje
	// a una oferta concreta. Se abrirÃ­a dicha conversaciÃ³n por primera vez.
	// Comprobar que el mensaje aparece
	// en el listado de mensajes.
	@Test
	public void PR31() {
		// Vamos al formulario de logueo.
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		// Rellenamos el formulario con admin
		PO_LoginView.fillForm(driver, "prueba@prueba.com", "123456");
		// Click en usuarios

		List<WebElement> elementos = PO_View.checkElement(driver, "class", "all");
		elementos.get(0).click();

		// Pinchamos en la opcioÌ�n de lista de usuarios.
		elementos = PO_View.checkElement(driver, "free", "//a[contains(@href,'offer/list')]");
		elementos.get(0).click();
		WebElement tituloe = driver.findElement(By.name("searchText"));
		tituloe.click();
		tituloe.clear();
		tituloe.sendKeys("OPEL24");
		// Pulsamos boton buscar
		By boton = By.className("busqueda");
		driver.findElement(boton).click();

		List<WebElement> elementos1 = SeleniumUtils.EsperaCargaPagina(driver, "free", "//tbody", PO_View.getTimeout());

		boton = By.className("details");
		driver.findElement(boton).click();

		tituloe = driver.findElement(By.name("content"));
		tituloe.click();
		tituloe.clear();
		tituloe.sendKeys("Me interesa");
		boton = By.className("enviachat");
		driver.findElement(boton).click();
		// Comprobamos mensaje
		PO_View.checkElement(driver, "text", "Me interesa");
		// Salimos de sesion
		PO_PrivateView.clickOption(driver, "logout", "class", "btn btn-primary");

	}

	// [Prueba32] Sobre el listado de conversaciones enviar un mensaje a una
	// conversaciÃ³n ya abierta.
	// Comprobar que el mensaje aparece en la lista de mensajes.
	@Test
	public void PR32() {
		// Vamos al formulario de logueo.
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		// Rellenamos el formulario con admin
		PO_LoginView.fillForm(driver, "prueba@prueba.com", "123456");
		// Click en usuarios

		List<WebElement> elementos = PO_View.checkElement(driver, "class", "messages");
		elementos.get(0).click();
		// Pinchamos en un mensaje.
		elementos = PO_View.checkElement(driver, "free", "//a[contains(@href,'/chat/conversation/')]");
		elementos.get(0).click();
		WebElement tituloe = driver.findElement(By.name("content"));
		tituloe.click();
		tituloe.clear();
		tituloe.sendKeys("adjudicado");
		By boton = By.className("enviachat");
		driver.findElement(boton).click();
		// Comprobamos mensaje
		PO_View.checkElement(driver, "text", "adjudicado");
		// Salimos de sesion
		PO_PrivateView.clickOption(driver, "logout", "class", "btn btn-primary");


	}

	// [Prueba33] Mostrar el listado de conversaciones ya abiertas. Comprobar que el
	// listado contiene las
	// conversaciones que deben ser.
	@Test
	public void PR33() {
		// Vamos al formulario de logueo.
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		// Rellenamos el formulario con admin
		PO_LoginView.fillForm(driver, "prueba@prueba.com", "123456");
		// Click en usuarios

		List<WebElement> elementos = PO_View.checkElement(driver, "class", "messages");
		elementos.get(0).click();

		List<WebElement> elementos1 = SeleniumUtils.EsperaCargaPagina(driver, "free", "//tbody/tr",
				PO_View.getTimeout());
		//Solo hay un chat
		assertEquals(1, elementos1.size());
		// Salimos de sesion
		PO_PrivateView.clickOption(driver, "logout", "class", "btn btn-primary");

	}

	// [Prueba34] Sobre el listado de conversaciones ya abiertas. Pinchar el enlace
	// Eliminar de la primera y
	// comprobar que el listado se actualiza correctamente.
	@Test
	public void PR34() {
		// Vamos al formulario de logueo.
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		// Rellenamos el formulario con admin
		PO_LoginView.fillForm(driver, "prueba@prueba.com", "123456");
		// Click en usuarios

		List<WebElement> elementos = PO_View.checkElement(driver, "class", "messages");
		elementos.get(0).click();

		List<WebElement> elementos1 = SeleniumUtils.EsperaCargaPagina(driver, "free", "//tbody/tr",
				PO_View.getTimeout());
		//Solo hay un chat
		assertEquals(1, elementos1.size());
		// Salimos de sesion
		PO_PrivateView.clickOption(driver, "logout", "class", "btn btn-primary");

	}

	// [Prueba35] Sobre el listado de conversaciones ya abiertas. Pinchar el enlace
	// Eliminar de la Ãºltima y
	// comprobar que el listado se actualiza correctamente.
	@Test
	public void PR35() {
		// Vamos al formulario de logueo.
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		// Rellenamos el formulario con admin
		PO_LoginView.fillForm(driver, "prueba@prueba.com", "123456");
		// Click en usuarios

		List<WebElement> elementos = PO_View.checkElement(driver, "class", "all");
		elementos.get(0).click();

		// Pinchamos en la opcioÌ�n de lista de usuarios.
		elementos = PO_View.checkElement(driver, "free", "//a[contains(@href,'offer/list')]");
		elementos.get(0).click();
		WebElement tituloe = driver.findElement(By.name("searchText"));
		tituloe.click();
		tituloe.clear();
		tituloe.sendKeys("OPEL24");
		// Pulsamos boton buscar
		By boton = By.className("busqueda");
		driver.findElement(boton).click();

		List<WebElement> elementos1 = SeleniumUtils.EsperaCargaPagina(driver, "free", "//tbody", PO_View.getTimeout());

		boton = By.className("details");
		driver.findElement(boton).click();

		tituloe = driver.findElement(By.name("content"));
		tituloe.click();
		tituloe.clear();
		tituloe.sendKeys("Me interesa");
		boton = By.className("enviachat");
		driver.findElement(boton).click();
		// Comprobamos mensaje
		PO_View.checkElement(driver, "text", "Me interesa");
		// Salimos de sesion
		PO_PrivateView.clickOption(driver, "logout", "class", "btn btn-primary");

		// Vamos al formulario de logueo.
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		// Rellenamos el formulario con admin
		PO_LoginView.fillForm(driver, "prueba@prueba.com", "123456");
		// Click en usuarios

		elementos = PO_View.checkElement(driver, "class", "messages");
		elementos.get(0).click();

		elementos1 = SeleniumUtils.EsperaCargaPagina(driver, "free", "//tbody/tr",
				PO_View.getTimeout());
		//Solo hay un chat
		assertEquals(1, elementos1.size());
		// Salimos de sesion
		PO_PrivateView.clickOption(driver, "logout", "class", "btn btn-primary");

	}

	//
	// [Prueba36] Al crear una oferta marcar dicha oferta como destacada y a
	// continuaciÃ³n comprobar: i) que
	// aparece en el listado de ofertas destacadas para los usuarios y que el saldo
	// del usuario se actualiza
	// adecuadamente en la vista del ofertante (-20).
	@Test
	public void PR36() {
		// Vamos al formulario de logueo.
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		// Rellenamos el formulario con usuario
		PO_LoginView.fillForm(driver, "123@prueba.com", "123456");
		// Click en usuarios

		List<WebElement> elementos = PO_View.checkElement(driver, "free", "//li[contains(@id, 'offers-menu')]/a");
		elementos.get(0).click();

		// Pinchamos en la opcioÌ�n de aÃ±adir oferta y aÃ±adimos.
		elementos = PO_View.checkElement(driver, "free", "//a[contains(@href,'offer/add')]");
		elementos.get(0).click();
		PO_AddOffer.fillFormSpecial(driver, "titulodeofertadepruebaparadestacada", "descripciondeofeertanussseva", "1000", "http://pruebaurl.com");
		elementos = PO_View.checkElement(driver, "free",
				"//input[contains(@type, 'checkbox')]");
		elementos.get(0).click();
		By boton = By.className("btn");
		driver.findElement(boton).click();

		// Comprobamos que sale

		elementos = PO_View.checkElement(driver, "free", "//a[contains(@href,'/home')]");
		elementos.get(0).click();
		PO_View.checkElement(driver, "text", "180.0");

		// Salimos de sesion
		PO_PrivateView.clickOption(driver, "logout", "class", "btn btn-primary");

	}

	// [Prueba37] Sobre el listado de ofertas de un usuario con menos de 20 euros de
	// saldo, pinchar en el
	// enlace Destacada y a continuaciÃ³n comprobar: i) que aparece en el listado de
	// ofertas destacadas para los
	// usuarios y que el saldo del usuario se actualiza adecuadamente en la vista
	// del ofertante (-20).
	@Test
	public void PR37() {
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		// Rellenamos el formulario con admin
		PO_LoginView.fillForm(driver, "prueba@prueba.com", "123456");
		// Click en usuarios

		List<WebElement> elementos = PO_View.checkElement(driver, "free", "//li[contains(@id, 'offers-menu')]/a");
		elementos.get(0).click();

		PO_View.checkElement(driver, "text", "titulodeofertadepruebaparadestacada");
		// Salimos de sesion
		PO_PrivateView.clickOption(driver, "logout", "class", "btn btn-primary");


	}

	// [Prueba38] Sobre el listado de ofertas de un usuario con menos de 20 euros de
	// saldo, pinchar en el
	// enlace Destacada y a continuaciÃ³n comprobar que se muestra el mensaje de
	// saldo no suficiente.
	@Test
	public void PR38() {
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		// Rellenamos el formulario con admin
		PO_LoginView.fillForm(driver, "prueba@prueba.com", "123456");
		// Click en usuarios

		List<WebElement> elementos = PO_View.checkElement(driver, "free", "//li[contains(@id, 'offers-menu')]/a");
		elementos.get(0).click();

		// Pinchamos en la opcioÌ�n de lista de ofertas.
		elementos = PO_View.checkElement(driver, "free", "//a[contains(@href,'offer/mylist')]");
		elementos.get(0).click();
		List<WebElement> elementos1 = SeleniumUtils.EsperaCargaPagina(driver, "free", "//tbody/tr",
				PO_View.getTimeout());

		elementos = PO_View.checkElement(driver, "free",
				"//td[contains(text(), 'OPEL19')]/following-sibling::*/a[contains(@href, 'offer/special')]");
		elementos.get(0).click();

		PO_View.checkElement(driver, "text", "No se ha realizado la compra");
		// Salimos de sesion
		PO_PrivateView.clickOption(driver, "logout", "class", "btn btn-primary");

	}

	// Al finalizar la uÌ�ltima prueba
	@AfterClass
	static public void end() {
		// Cerramos el navegador al finalizar las pruebas

		driver.quit();
	}

}
