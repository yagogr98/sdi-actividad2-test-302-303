package test;

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

import test.pageobjects.PO_AddOffer;
import test.pageobjects.PO_HomeView;
import test.pageobjects.PO_LoginView;
import test.pageobjects.PO_NavView;
import test.pageobjects.PO_PrivateView;
import test.pageobjects.PO_Register;
import test.pageobjects.PO_View;
import test.util.SeleniumUtils;;


@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class myWallapopTest {
	// En Windows (Debe ser la versioÌ�n 65.0.1 y desactivar las actualizacioens
	// automaÌ�ticas)):
	//static String PathFirefox64 = "C:\\Program Files\\Mozilla Firefox\\firefox.exe";
	//static String Geckdriver022 = "C:\\path\\geckodriver024win64.exe";
	// En MACOSX (Debe ser la versioÌ�n 65.0.1 y desactivar las actualizacioens
	// automaÌ�ticas):
		static String PathFirefox65 = "/Applications/Firefox.app/Contents/MacOS/firefox-bin";
		static String Geckdriver024 = "/Users/yagogarciarodriguez/Downloads/a/geckodriver024mac";
	// ComuÌ�n a Windows y a MACOSX
	static WebDriver driver = getDriver(PathFirefox65, Geckdriver024);
	static String URLlocal = "http://localhost:8081";
	static String URLremota = "http://ec2-34-224-173-6.compute-1.amazonaws.com:8080";
	static String URL = URLlocal; // Se va a probar con la URL remota, sino URL=URLlocal

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
//
	// DespueÌ�s de cada prueba se borran las cookies del navegador
	@After
	public void tearDown() {
		driver.manage().deleteAllCookies();
	}

	//TODO Fijar la bbdd
	// Antes de la primera prueba
	@BeforeClass
	static public void begin() {
		//driver.navigate().to("http://localhost:8081/test");
		driver.navigate().to(URL);
		
		// Vamos al formulario de logueo.
		PO_HomeView.clickOption(driver, "/registrarse", "class", "signup");
		// Rellenamos el formulario
		PO_Register.fillForm(driver, "1@prueba.com", "1", "1", "1", "1");
		// Miramos que seguimos en la misma pagina
		
		// Vamos al formulario de logueo.
		PO_HomeView.clickOption(driver, "/registrarse", "class", "signup");
		// Rellenamos el formulario
		PO_Register.fillForm(driver, "12@prueba.com", "12", "12", "12", "12");
		// Miramos que seguimos en la misma pagina
		
		// Vamos al formulario de logueo.
		PO_HomeView.clickOption(driver, "/registrarse", "class", "signup");
		// Rellenamos el formulario
		PO_Register.fillForm(driver, "123@prueba.com", "123", "123", "123", "123");
		// Miramos que seguimos en la misma pagina
		
		// Vamos al formulario de logueo.
		PO_HomeView.clickOption(driver, "/registrarse", "class", "signup");
		// Rellenamos el formulario
		PO_Register.fillForm(driver, "1234@prueba.com", "1234", "1234", "1234", "1234");
		// Miramos que seguimos en la misma pagina
		
		// Vamos al formulario de logueo.
		PO_HomeView.clickOption(driver, "/registrarse", "class", "signup");
		// Rellenamos el formulario
		PO_Register.fillForm(driver, "12345@prueba.com", "12345", "12345", "12345", "12345");
		// Miramos que seguimos en la misma pagina
		
		// Vamos al formulario de logueo.
		PO_HomeView.clickOption(driver, "/registrarse", "class", "signup");
		// Rellenamos el formulario
		PO_Register.fillForm(driver, "123456@prueba.com", "123456", "123456", "123456", "123456");
		// Miramos que seguimos en la misma pagina
		
		// Vamos al formulario de logueo.
		PO_HomeView.clickOption(driver, "/registrarse", "class", "signup");
		// Rellenamos el formulario
		PO_Register.fillForm(driver, "prueba@prueba.com", "prueba", "prueba", "prueba", "prueba");
		// Miramos que seguimos en la misma pagina
		
		
		// Vamos al formulario de logueo.
		PO_HomeView.clickOption(driver, "/registrarse", "class", "signup");
		// Rellenamos el formulario
		PO_Register.fillForm(driver, "admin@admin.com", "admin", "admin", "admin", "admin");
		// Miramos que seguimos en la misma pagina
	}


	// [Prueba1] Registro de Usuario con datos invÃ¡lidos (email vacio).

	@Test
	public void PR01() {
		// Vamos al formulario de logueo.
		PO_HomeView.clickOption(driver, "/registrarse", "class", "signup");
		// Rellenamos el formulario
		PO_Register.fillForm(driver, "", "12345", "12345", "12345", "12345");
		// Miramos que seguimos en la misma pagina
		PO_View.checkElement(driver, "text", "Email");
	}

	// [Prueba2] Registro de Usuario con datos invÃ¡lidos (repeticiÃ³n de contraseÃ±a
	// invÃ¡lida).
	@Test
	public void PR02() {// Vamos al formulario de logueo.
		PO_HomeView.clickOption(driver, "/registrarse", "class", "signup");
		// Rellenamos el formulario
		PO_Register.fillForm(driver, "123@prueba.com", "12345", "12345", "12345", "54321");
		// Miramos que seguimos en la misma pagina
		PO_View.checkElement(driver, "text", "Email");
	}
	
	// [Prueba3] Registro de Usuario con datos invalidos (email existente).
	@Test
	public void PR03() {
		PO_HomeView.clickOption(driver, "/registrarse", "class", "signup");
		// Rellenamos el formulario
		PO_Register.fillForm(driver, "123@prueba.com", "12345", "12345", "12345", "12345");
		// Miramos que seguimos en la misma pagina
		PO_View.checkElement(driver, "text", "Email");
	}
	

	// [Prueba5] Inicio de sesiÃ³n con datos vÃ¡lidos (administrador).
//	@Test
//	public void PR05() {
//		// Vamos al formulario de logueo.
//		PO_HomeView.clickOption(driver, "/identificarse", "class", "signup");
//		// Rellenamos el formulario
//		PO_LoginView.fillForm(driver, "admin@email.com", "admin");
//		// COmprobamos que entramos en la pagina de inicio
//		PO_View.checkElement(driver, "text", "admin@email.com");
//	}
	

	// [Prueba4] Inicio de sesion con datos validos (usuario estandar).
	@Test
	public void PR04() {
		// Vamos al formulario de logueo.
		PO_NavView.clickOption(driver, "/identificarse", "class", "signup");
		// Rellenamos el formulario
		PO_LoginView.fillForm(driver, "prueba@prueba.com", "prueba");
		// COmprobamos que entramos en la pagina de inicio
		PO_View.checkElement(driver, "text", "prueba@prueba.com");
	}
	
	// [Prueba5] Inicio de sesiÃ³n con datos vÃ¡lidos (usuario estÃ¡ndar, email
	// existente, pero contraseÃ±a
	// incorrecta).
	@Test
	public void PR05() {
		// Vamos al formulario de logueo.
		PO_HomeView.clickOption(driver, "/identificarse", "class", "signup");
		// Rellenamos el formulario
		PO_LoginView.fillForm(driver, "prueba@prueba.com", "");
		// COmprobamos que estamos en la pagina de login
		PO_View.checkElement(driver, "text", "Email");
	}
	
	// [Prueba6] Inicio de sesiÃ³n con datos invÃ¡lidos (usuario estÃ¡ndar, campo email
	// y contraseÃ±a vacÃ­os).
	@Test
	public void PR06() {
		// Vamos al formulario de logueo.
		PO_HomeView.clickOption(driver, "/identificarse", "class", "signup");
		// Rellenamos el formulario
		PO_LoginView.fillForm(driver, "", "");
		// COmprobamos que estamos en la pagina de login
		PO_View.checkElement(driver, "text", "Email");
	}



	// [Prueba7] Inicio de sesiÃ³n con datos invÃ¡lidos (usuario estÃ¡ndar, email no
	// existente en la aplicaciÃ³n).
	@Test
	public void PR07() {
		// Vamos al formulario de logueo.
		PO_HomeView.clickOption(driver, "/identificarse", "class", "signup");
		// Rellenamos el formulario
		PO_LoginView.fillForm(driver, "emailinventado@l.com", "123456");
		// COmprobamos que estamos en la pagina de login
		PO_View.checkElement(driver, "text", "Email");

	}

	// [Prueba8] Hacer click en la opciÃ³n de salir de sesiÃ³n y comprobar que se
	// redirige a la pÃ¡gina de inicio
	// de sesiÃ³n (Login).
	@Test
	public void PR08() {
		// Vamos al formulario de logueo.
		PO_HomeView.clickOption(driver, "/identificarse", "class", "signup");
		// Rellenamos el formulario
		PO_LoginView.fillForm(driver, "prueba@prueba.com", "prueba");
		// Ahora nos desconectamos
		PO_HomeView.clickOption(driver, "/desconectarse", "class", "btn btn-primary");
		// COmprobamos que entramos en la pagina de login
		PO_View.checkElement(driver, "text", "Email");

	}
	// [Prueba9] Comprobar que el botÃ³n cerrar sesiÃ³n no estÃ¡ visible si el usuario
	// no estÃ¡ autenticado.

	@Test
	public void PR09() {
		// Vamos al formulario de logueo.
		PO_HomeView.clickOption(driver, "/identificarse", "class", "signup");
		// Rellenamos el formulario
		PO_LoginView.fillForm(driver, "prueba@prueba.com", "prueba");
		// Ahora nos desconectamos
		PO_HomeView.clickOption(driver, "/desconectarse", "class", "btn btn-primary");
		// COmprobamos que entramos en la pagina de login
		PO_View.checkElement(driver, "text", "Email");

		SeleniumUtils.EsperaCargaPaginaNoTexto(driver, "Desconectarse", PO_View.getTimeout());
	}

	// [Prueba10] Mostrar el listado de usuarios y comprobar que se muestran todos
	// los que existen en el
	// sistema
	@Test
	public void PR10() {
		// Vamos al formulario de logueo.
		PO_HomeView.clickOption(driver, "/identificarse", "class", "signup");
		// Rellenamos el formulario con admin
		PO_LoginView.fillForm(driver, "admin@admin.com", "admin");
		List<WebElement> elementos1 = SeleniumUtils.EsperaCargaPagina(driver, "free", "//tbody/tr",
				PO_View.getTimeout());

		assertEquals(8, elementos1.size());
		// Salimos de sesion
		driver.navigate().to("http://localhost:8081/desconectarse");

	}

	// [Prueba11] Ir a la lista de usuarios, borrar el primer usuario de la lista,
	// comprobar que la lista se actualiza
	// y dicho usuario desaparece.
	@Test
	public void PR11() {
		// Vamos al formulario de logueo.
		PO_HomeView.clickOption(driver, "/identificarse", "class", "signup");
		// Rellenamos el formulario con admin
		PO_LoginView.fillForm(driver, "admin@admin.com", "admin");
		List<WebElement> elementos = SeleniumUtils.EsperaCargaPagina(driver, "free", "//tbody/tr",
				PO_View.getTimeout());
		elementos = PO_View.checkElement(driver, "free",
				"//td[contains(text(), '1')]/following-sibling::*/input[contains(@id, 'chkbox')]");
		elementos.get(0).click();
		By boton = By.id("delete");
		driver.findElement(boton).click();
		elementos = SeleniumUtils.EsperaCargaPagina(driver, "free", "//tbody/tr", PO_View.getTimeout());
		SeleniumUtils.EsperaCargaPaginaNoTexto(driver, "1", PO_View.getTimeout());
		// Salimos de sesion
		driver.navigate().to("http://localhost:8081/desconectarse");


	}

	// [Prueba12] Ir a la lista de usuarios, borrar el Ãºltimo usuario de la lista,
	// comprobar que la lista se actualiza
	// y dicho usuario desaparece.
	@Test
	public void PR12() {
//		PO_HomeView.clickOption(driver, "/registrarse", "class", "signup");
//		// Rellenamos el formulario
//		PO_Register.fillForm(driver, "prueba@prueba.com", "12345", "12345", "12345", "12345");
//		// Miramos que seguimos en la misma pagina
//		PO_View.checkElement(driver, "text", "prueba@prueba.com");
//		driver.navigate().to("http://localhost:8081/desconectarse");
		// Vamos al formulario de logueo.
		PO_HomeView.clickOption(driver, "/identificarse", "class", "signup");
		// Rellenamos el formulario con admin
		PO_LoginView.fillForm(driver, "admin@admin.com", "admin");
		// Click en usuarios
		List<WebElement> elementos = SeleniumUtils.EsperaCargaPagina(driver, "free", "//tbody/tr",
				PO_View.getTimeout());
		elementos = PO_View.checkElement(driver, "free",
				"//td[contains(text(), '123456')]/following-sibling::*/input[contains(@type, 'checkbox')]");
		elementos.get(0).click();
		By boton = By.id("delete");
		driver.findElement(boton).click();

		elementos = SeleniumUtils.EsperaCargaPagina(driver, "free", "//tbody/tr", PO_View.getTimeout());
		System.out.print(elementos.size());
		SeleniumUtils.EsperaCargaPaginaNoTexto(driver, "ultimo@prueba.com", PO_View.getTimeout());
		// Salimos de sesion
		driver.navigate().to("http://localhost:8081/desconectarse");

	}

	// [Prueba13] Ir a la lista de usuarios, borrar 3 usuarios, comprobar que la
	// lista se actualiza y dichos
	// usuarios desaparecen.
	@Test
	public void PR13() {
		PO_HomeView.clickOption(driver, "/registrarse", "class", "signup");
		// Rellenamos el formulario
		PO_Register.fillForm(driver, "123@prueba.com", "123", "123", "123", "123");
		// Miramos que seguimos en la misma pagina
		driver.navigate().to("http://localhost:8081/desconectarse");
		
		PO_HomeView.clickOption(driver, "/registrarse", "class", "signup");
		// Rellenamos el formulario
		PO_Register.fillForm(driver, "1234@prueba.com", "1234", "1234", "1234", "1234");
		// Miramos que seguimos en la misma pagina
		driver.navigate().to("http://localhost:8081/desconectarse");
		
		PO_HomeView.clickOption(driver, "/identificarse", "class", "signup");
		
		PO_HomeView.clickOption(driver, "/registrarse", "class", "signup");
		// Rellenamos el formulario
		PO_Register.fillForm(driver, "12345@prueba.com", "12345", "12345", "12345", "12345");
		// Miramos que seguimos en la misma pagina
		driver.navigate().to("http://localhost:8081/desconectarse");
		PO_LoginView.fillForm(driver, "admin@admin.com", "admin");
		// Click en usuarios
		List<WebElement> elementos = SeleniumUtils.EsperaCargaPagina(driver, "free", "//tbody/tr",
				PO_View.getTimeout());
		elementos = PO_View.checkElement(driver, "free",
				"//td[contains(text(), '123@prueba.com')]/following-sibling::*/input[contains(@type, 'checkbox')]");
		elementos.get(0).click();
		elementos = PO_View.checkElement(driver, "free",
				"//td[contains(text(), '1234@prueba.com')]/following-sibling::*/input[contains(@type, 'checkbox')]");
		elementos.get(0).click();
		elementos = PO_View.checkElement(driver, "free",
				"//td[contains(text(), '12345@prueba.com')]/following-sibling::*/input[contains(@type, 'checkbox')]");
		elementos.get(0).click();
		By boton = By.id("delete");
		driver.findElement(boton).click();
		driver.navigate().to("http://localhost:8081/admin");
		SeleniumUtils.textoNoPresentePagina(driver, "123@prueba.com");
		SeleniumUtils.textoNoPresentePagina(driver, "1234@prueba.com");
		SeleniumUtils.textoNoPresentePagina(driver, "12345@prueba.com");
		// Salimos de sesion
		driver.navigate().to("http://localhost:8081/desconectarse");

	}

	// [Prueba14] Ir al formulario de alta de oferta, rellenarla con datos vÃ¡lidos y
	// pulsar el botÃ³n Submit.
	// Comprobar que la oferta sale en el listado de ofertas de dicho usuario.
	@Test
	public void PR14() {
		// Vamos al formulario de logueo.
		PO_HomeView.clickOption(driver, "/identificarse", "class", "signup");
		// Rellenamos el formulario con usuario
		PO_LoginView.fillForm(driver, "prueba@prueba.com", "prueba");

		driver.navigate().to("http://localhost:8081/productos/agregar");
		
		PO_AddOffer.fillForm(driver, "titulodeofertadeprueba", "descripciondeofeertanueva", "1");

		driver.navigate().to("http://localhost:8081/publicaciones");

		SeleniumUtils.textoPresentePagina(driver, "titulodeofertadeprueba");

		// Salimos de sesion
		PO_PrivateView.clickOption(driver, "/desconectarse", "class", "btn btn-primary");

	}

	// [Prueba15] Ir al formulario de alta de oferta, rellenarla con datos invÃ¡lidos
	// (campo tÃ­tulo vacÃ­o) y pulsar
	// el botÃ³n Submit. Comprobar que se muestra el mensaje de campo obligatorio.
	@Test
	public void PR15() {
		// Vamos al formulario de logueo.
				PO_HomeView.clickOption(driver, "/identificarse", "class", "signup");
				// Rellenamos el formulario con usuario
				PO_LoginView.fillForm(driver, "prueba@prueba.com", "prueba");

				driver.navigate().to("http://localhost:8081/productos/agregar");
				
				PO_AddOffer.fillForm(driver, "", "descripciondeofeertanueva", "1");
				SeleniumUtils.textoPresentePagina(driver, "Agregar producto");

				// Salimos de sesion
				driver.navigate().to("http://localhost:8081/desconectarse");

	}

	// [Prueba16] Mostrar el listado de ofertas para dicho usuario y comprobar que
	// se muestran todas los que
	// existen para este usuario.
	@Test
	public void PR16() {
		PO_HomeView.clickOption(driver, "/identificarse", "class", "signup");
		// Rellenamos el formulario con usuario
		PO_LoginView.fillForm(driver, "prueba@prueba.com", "prueba");
		driver.navigate().to("http://localhost:8081/publicaciones");
		List<WebElement> elementos = SeleniumUtils.EsperaCargaPagina(driver, "free", "//tbody/tr",
				PO_View.getTimeout());
		assertEquals(1, elementos.size());
		

		// Salimos de sesion
		PO_PrivateView.clickOption(driver, "/desconectarse", "class", "btn btn-primary");

	}

	// [Prueba17] Ir a la lista de ofertas, borrar la primera oferta de la lista,
	// comprobar que la lista se actualiza y
	// que la oferta desaparece.
	@Test
	public void PR17() {
		// Vamos al formulario de logueo.
		PO_HomeView.clickOption(driver, "/identificarse", "class", "signup");
		// Rellenamos el formulario con admin
		PO_LoginView.fillForm(driver, "prueba@prueba.com", "prueba");
		driver.navigate().to("http://localhost:8081/productos/agregar");
		
		PO_AddOffer.fillForm(driver, "titulodeofertadeprueba2222", "descripciondeofeertanueva", "1");
		
		driver.navigate().to("http://localhost:8081/productos/agregar");
		
		PO_AddOffer.fillForm(driver, "titulodeofertadeprueba22222", "descripciondeofeertanueva", "1");

		driver.navigate().to("http://localhost:8081/publicaciones");


		// Pinchamos en la opcioÌ�n de lista de usuarios.
		List<WebElement> elementos = PO_View.checkElement(driver, "free", "//a[contains(@name,'deletebutton')]");
		elementos.get(0).click();
		
		driver.navigate().to("http://localhost:8081/publicaciones");
		elementos = SeleniumUtils.EsperaCargaPagina(driver, "free", "//tbody/tr",
				PO_View.getTimeout());
		assertEquals(2, elementos.size());
		PO_PrivateView.clickOption(driver, "/desconectarse", "class", "btn btn-primary");
		

	}

	// [Prueba18] Ir a la lista de ofertas, borrar la Ãºltima oferta de la lista,
	// comprobar que la lista se actualiza y
	// que la oferta desaparece.
	@Test
	public void PR18() {
		// Vamos al formulario de logueo.
		PO_HomeView.clickOption(driver, "/identificarse", "class", "signup");
		// Rellenamos el formulario con admin
		PO_LoginView.fillForm(driver, "prueba@prueba.com", "prueba");
		driver.navigate().to("http://localhost:8081/productos/agregar");
		
		PO_AddOffer.fillForm(driver, "titulodeofertadeprueba", "descripciondeofeertanueva", "1");

		driver.navigate().to("http://localhost:8081/publicaciones");


		// Pinchamos en la opcioÌ�n de lista de usuarios.
		List<WebElement> elementos = PO_View.checkElement(driver, "free", "//a[contains(@name,'deletebutton')]");
		elementos.get(2).click();
		
		driver.navigate().to("http://localhost:8081/publicaciones");
		elementos = SeleniumUtils.EsperaCargaPagina(driver, "free", "//tbody/tr",
				PO_View.getTimeout());
		assertEquals(2, elementos.size());
		PO_PrivateView.clickOption(driver, "/desconectarse", "class", "btn btn-primary");

	}

	// [Prueba19] Hacer una bÃºsqueda con el campo vacÃ­o y comprobar que se muestra la
	// pÃ¡gina que
	// corresponde con el listado de las ofertas existentes en el sistema
	@Test
	public void PR19() {
		// Vamos al formulario de logueo.
		PO_HomeView.clickOption(driver, "/identificarse", "class", "signup");
		// Rellenamos el formulario con usuario
		PO_LoginView.fillForm(driver, "prueba@prueba.com", "prueba");
		// Click en usuarios

		driver.navigate().to("http://localhost:8081/tienda");
		SeleniumUtils.textoPresentePagina(driver, "titulodeofertadeprueba2222");
		SeleniumUtils.textoPresentePagina(driver, "titulodeofertadeprueba22222");

		// Salimos de sesion
		PO_PrivateView.clickOption(driver, "/desconectarse", "class", "btn btn-primary");

	}

	// [Prueba20] Hacer una bÃºsqueda escribiendo en el campo un texto que no exista
	// y comprobar que se
	// muestra la pÃ¡gina que corresponde, con la lista de ofertas vacÃ­a.
	@Test
	public void PR20() {
		// Vamos al formulario de logueo.
		PO_HomeView.clickOption(driver, "/identificarse", "class", "signup");
		// Rellenamos el formulario con admin
		PO_LoginView.fillForm(driver, "prueba@prueba.com", "prueba");
		// Click en usuarios
		driver.navigate().to("http://localhost:8081/tienda");
		WebElement tituloe = driver.findElement(By.id("search"));
		tituloe.click();
		tituloe.clear();
		tituloe.sendKeys("asldkmasldkmalskdm");
		// Pulsamos boton buscar
		By boton = By.className("btn");
		driver.findElement(boton).click();
		SeleniumUtils.textoNoPresentePagina(driver, "titulodeofertadeprueba2222");
		SeleniumUtils.textoNoPresentePagina(driver, "titulodeofertadeprueba22222");
		PO_PrivateView.clickOption(driver, "/desconectarse", "class", "btn btn-primary");

	}
	
//	[Prueba21] Hacer una búsqueda escribiendo en el campo un texto en minúscula o mayúscula y
//	comprobar que se muestra la página que corresponde, con la lista de ofertas que contengan dicho texto,
//	independientemente que el título esté almacenado en minúsculas o mayúscula.
	//TODO
		@Test
		public void PR21() {
			// Vamos al formulario de logueo.
			PO_HomeView.clickOption(driver, "/identificarse", "class", "signup");
			// Rellenamos el formulario con admin
			PO_LoginView.fillForm(driver, "prueba@prueba.com", "prueba");
			// Click en usuarios
			driver.navigate().to("http://localhost:8081/tienda");
			WebElement tituloe = driver.findElement(By.id("search"));
			tituloe.click();
			tituloe.clear();
			tituloe.sendKeys("titulodeofertadeprueba2222");
			// Pulsamos boton buscar
			By boton = By.className("btn");
			driver.findElement(boton).click();
			SeleniumUtils.textoPresentePagina(driver, "titulodeofertadeprueba2222");
			PO_PrivateView.clickOption(driver, "/desconectarse", "class", "btn btn-primary");

		}

	// Prueba22] Sobre una bÃºsqueda determinada (a elecciÃ³n de desarrollador),
	// comprar una oferta que deja
	// un saldo positivo en el contador del comprobador. Y comprobar que el contador
	// se actualiza
	// correctamente en la vista del comprador.
	@Test
	public void PR22() {
		// Vamos al formulario de logueo.
		PO_HomeView.clickOption(driver, "/identificarse", "class", "signup");
		// Rellenamos el formulario con admin
		PO_LoginView.fillForm(driver, "prueba@prueba.com", "prueba");
		// Click en usuarios
		driver.navigate().to("http://localhost:8081/tienda");
		WebElement tituloe = driver.findElement(By.id("search"));
		tituloe.click();
		tituloe.clear();
		tituloe.sendKeys("titulodeofertadeprueba2222");
		// Pulsamos boton buscar
		By boton = By.className("btn");
		driver.findElement(boton).click();
		SeleniumUtils.textoPresentePagina(driver, "titulodeofertadeprueba2222");
		List<WebElement> elementos = PO_View.checkElement(driver, "free", "//html/body/div[1]/div[2]/div[1]/div/a/div[1]");
		elementos.get(0).click();
		elementos = PO_View.checkElement(driver, "free", "//html/body/div[1]/div[1]/div/div[2]/a");
		elementos.get(0).click();
		SeleniumUtils.textoPresentePagina(driver, "Dinero: 99");
		

	}

	// [Prueba23] Sobre una bÃºsqueda determinada (a elecciÃ³n de desarrollador),
	// comprar una oferta que deja
	// un saldo 0 en el contador del comprobador. Y comprobar que el contador se
	// actualiza correctamente en
	// la vista del comprador.
	@Test
	public void PR23() {
		// Vamos al formulario de logueo.
		PO_HomeView.clickOption(driver, "/identificarse", "class", "signup");
		// Rellenamos el formulario con admin
		PO_LoginView.fillForm(driver, "prueba@prueba.com", "123456");
		// Click en usuarios
		PO_LoginView.fillForm(driver, "prueba@prueba.com", "prueba");
		driver.navigate().to("http://localhost:8081/productos/agregar");
		
		PO_AddOffer.fillForm(driver, "ofertacara", "oferta", "100");
		PO_PrivateView.clickOption(driver, "/desconectarse", "class", "btn btn-primary");
		// Vamos al formulario de logueo.
		PO_HomeView.clickOption(driver, "/identificarse", "class", "signup");
		// Rellenamos el formulario con admin
		PO_LoginView.fillForm(driver, "12@prueba.com", "12");
		
		driver.navigate().to("http://localhost:8081/tienda");
		WebElement tituloe = driver.findElement(By.id("search"));
		tituloe.click();
		tituloe.clear();
		tituloe.sendKeys("ofertacara");
		// Pulsamos boton buscar
		By boton = By.className("btn");
		driver.findElement(boton).click();
		SeleniumUtils.textoPresentePagina(driver, "cara");
		List<WebElement> elementos = PO_View.checkElement(driver, "free", "//html/body/div[1]/div[2]/div[1]/div/a/div[1]");
		elementos.get(0).click();
		elementos = PO_View.checkElement(driver, "free", "//html/body/div[1]/div[1]/div/div[2]/a");
		elementos.get(0).click();
		SeleniumUtils.textoPresentePagina(driver, "Dinero: 0");

	}

	// [Prueba24] Sobre una bÃºsqueda determinada (a elecciÃ³n de desarrollador),
	// intentar comprar una oferta
	// que estÃ© por encima de saldo disponible del comprador. Y comprobar que se
	// muestra el mensaje de
	// saldo no suficiente.
	@Test
	public void PR24() {
		// Vamos al formulario de logueo.
		PO_HomeView.clickOption(driver, "/identificarse", "class", "signup");
		// Rellenamos el formulario con admin
		PO_LoginView.fillForm(driver, "prueba@prueba.com", "123456");
		// Click en usuarios
		PO_LoginView.fillForm(driver, "prueba@prueba.com", "prueba");
		driver.navigate().to("http://localhost:8081/productos/agregar");
		
		PO_AddOffer.fillForm(driver, "ofertacara", "oferta", "100");
		PO_PrivateView.clickOption(driver, "/desconectarse", "class", "btn btn-primary");
		// Vamos al formulario de logueo.
		PO_HomeView.clickOption(driver, "/identificarse", "class", "signup");
		// Rellenamos el formulario con admin
		PO_LoginView.fillForm(driver, "12@prueba.com", "12");
		
		driver.navigate().to("http://localhost:8081/tienda");
		WebElement tituloe = driver.findElement(By.id("search"));
		tituloe.click();
		tituloe.clear();
		tituloe.sendKeys("ofertacara");
		// Pulsamos boton buscar
		By boton = By.className("btn");
		driver.findElement(boton).click();
		SeleniumUtils.textoPresentePagina(driver, "cara");
		List<WebElement> elementos = PO_View.checkElement(driver, "free", "//html/body/div[1]/div[2]/div[1]/div/a/div[1]");
		elementos.get(0).click();
		elementos = PO_View.checkElement(driver, "free", "//html/body/div[1]/div[1]/div/div[2]/a");
		elementos.get(0).click();
		SeleniumUtils.textoPresentePagina(driver, "No tienes saldo suficiente");

	}

	// [Prueba25] Ir a la opciÃ³n de ofertas compradas del usuario y mostrar la
	// lista. Comprobar que aparecen
	// las ofertas que deben aparecer.
	@Test
	public void PR25() {

		// Vamos al formulario de logueo.
		PO_HomeView.clickOption(driver, "/identificarse", "class", "signup");
		// Rellenamos el formulario con admin
		PO_LoginView.fillForm(driver, "prueba@prueba.com", "prueba");
		// Click en usuarios
		
		driver.navigate().to("http://localhost:8081/compras");
		PO_View.checkElement(driver, "text", "titulodeofertadeprueba2222");
		// Salimos de sesion
		PO_PrivateView.clickOption(driver, "/desconectarse", "class", "btn btn-primary");

	}

//	// [Prueba27] Visualizar al menos cuatro pÃ¡ginas en EspaÃ±ol/InglÃ©s/EspaÃ±ol
//	// (comprobando que algunas
//	// de las etiquetas cambian al idioma correspondiente). PÃ¡gina
//	// principal/Opciones Principales de
//	// Usuario/Listado de Usuarios de Admin/Vista de alta de Oferta.
//	@Test
//	public void PR27() {
//		//Login
//		PO_HomeView.clickOption(driver, "/identificarse", "class", "signup");
//		PO_LoginView.fillForm(driver, "prueba@prueba.com", "123456");
//		PO_View.checkElement(driver, "text", "Bienvenido");
//
//		//Cambio a ingles
//		List<WebElement> elementos = PO_View.checkElement(driver, "text", "Idioma");
//		elementos.get(0).click();
//		elementos = PO_View.checkElement(driver, "free", "//a[contains(@id,'btnEnglish')]");
//		elementos.get(0).click();
//
//		PO_View.checkElement(driver, "text", "Welcome");
//		
//		//Cambio a espaÃ±ol
//		elementos = PO_View.checkElement(driver, "text", "Language");
//		elementos.get(0).click();
//		elementos = PO_View.checkElement(driver, "free", "//a[contains(@id,'btnSpanish')]");
//		elementos.get(0).click();
//
//		PO_View.checkElement(driver, "text", "Bienvenido");
//
//		elementos = PO_View.checkElement(driver, "class", "all");
//		elementos.get(0).click();
//
//		driver.navigate().to(URL + "/offer/list");
//		PO_View.checkElement(driver, "text", "Mas Informacion");
//		elementos = PO_View.checkElement(driver, "text", "Idioma");
//		elementos.get(0).click();
//		elementos = PO_View.checkElement(driver, "free", "//a[contains(@id,'btnEnglish')]");
//		elementos.get(0).click();
//
//		PO_View.checkElement(driver, "text", "More");
//
//		elementos = PO_View.checkElement(driver, "text", "Language");
//		elementos.get(0).click();
//		elementos = PO_View.checkElement(driver, "free", "//a[contains(@id,'btnSpanish')]");
//		elementos.get(0).click();
//
//		PO_View.checkElement(driver, "text", "Mas Informacion");
//		
//		driver.navigate().to(URL + "/offer/add");
//		
//		PO_View.checkElement(driver, "text", "AÃ±adir");
//		elementos = PO_View.checkElement(driver, "text", "Idioma");
//		elementos.get(0).click();
//		elementos = PO_View.checkElement(driver, "free", "//a[contains(@id,'btnEnglish')]");
//		elementos.get(0).click();
//
//		PO_View.checkElement(driver, "text", "Special");
//
//		elementos = PO_View.checkElement(driver, "text", "Language");
//		elementos.get(0).click();
//		elementos = PO_View.checkElement(driver, "free", "//a[contains(@id,'btnSpanish')]");
//		elementos.get(0).click();
//
//		PO_View.checkElement(driver, "text", "AÃ±adir");
//		
//		driver.navigate().to(URL + "/home");
//		
//		PO_PrivateView.clickOption(driver, "/desconectarse", "class", "btn btn-primary");
//
//		// Rellenamos el formulario con admin
//		PO_LoginView.fillForm(driver, "admin@email.com", "admin");
//		
//		driver.navigate().to(URL + "/user/list");
//		
//		// Pinchamos en la opcioÌ�n de lista de usuarios.
//		PO_View.checkElement(driver, "text", "Apellidos");
//		elementos = PO_View.checkElement(driver, "text", "Idioma");
//		elementos.get(0).click();
//		elementos = PO_View.checkElement(driver, "free", "//a[contains(@id,'btnEnglish')]");
//		elementos.get(0).click();
//
//		PO_View.checkElement(driver, "text", "Surnames");
//
//		elementos = PO_View.checkElement(driver, "text", "Language");
//		elementos.get(0).click();
//		elementos = PO_View.checkElement(driver, "free", "//a[contains(@id,'btnSpanish')]");
//		elementos.get(0).click();
//
//		PO_View.checkElement(driver, "text", "Apellidos");
//		// Salimos de sesion
//		PO_PrivateView.clickOption(driver, "/desconectarse", "class", "btn btn-primary");
//	}


//	// [Prueba26] Al crear una oferta marcar dicha oferta como destacada y a
//	// continuaciÃ³n comprobar: i) que
//	// aparece en el listado de ofertas destacadas para los usuarios y que el saldo
//	// del usuario se actualiza
//	// adecuadamente en la vista del ofertante (-20).
//	@Test
//	public void PR26() {
//		// Vamos al formulario de logueo.
//		PO_HomeView.clickOption(driver, "/identificarse", "class", "signup");
//		// Rellenamos el formulario con usuario
//		PO_LoginView.fillForm(driver, "123@prueba.com", "123456");
//		// Click en usuarios
//
//		List<WebElement> elementos = PO_View.checkElement(driver, "free", "//li[contains(@id, 'offers-menu')]/a");
//		elementos.get(0).click();
//
//		// Pinchamos en la opcioÌ�n de aÃ±adir oferta y aÃ±adimos.
//		elementos = PO_View.checkElement(driver, "free", "//a[contains(@href,'offer/add')]");
//		elementos.get(0).click();
//		PO_AddOffer.fillFormSpecial(driver, "titulodeofertadepruebaparadestacada", "descripciondeofeertanussseva", "1000", "http://pruebaurl.com");
//		elementos = PO_View.checkElement(driver, "free",
//				"//input[contains(@type, 'checkbox')]");
//		elementos.get(0).click();
//		By boton = By.className("btn");
//		driver.findElement(boton).click();
//
//		// Comprobamos que sale
//
//		elementos = PO_View.checkElement(driver, "free", "//a[contains(@href,'/home')]");
//		elementos.get(0).click();
//		PO_View.checkElement(driver, "text", "180.0");
//
//		// Salimos de sesion
//		PO_PrivateView.clickOption(driver, "/desconectarse", "class", "btn btn-primary");
//
//	}
//
//	// [Prueba27] Sobre el listado de ofertas de un usuario con menos de 20 euros de
//	// saldo, pinchar en el
//	// enlace Destacada y a continuaciÃ³n comprobar: i) que aparece en el listado de
//	// ofertas destacadas para los
//	// usuarios y que el saldo del usuario se actualiza adecuadamente en la vista
//	// del ofertante (-20).
//	@Test
//	public void PR27() {
//		PO_HomeView.clickOption(driver, "/identificarse", "class", "signup");
//		// Rellenamos el formulario con admin
//		PO_LoginView.fillForm(driver, "prueba@prueba.com", "123456");
//		// Click en usuarios
//
//		List<WebElement> elementos = PO_View.checkElement(driver, "free", "//li[contains(@id, 'offers-menu')]/a");
//		elementos.get(0).click();
//
//		PO_View.checkElement(driver, "text", "titulodeofertadepruebaparadestacada");
//		// Salimos de sesion
//		PO_PrivateView.clickOption(driver, "/desconectarse", "class", "btn btn-primary");
//
//
//	}
//
//	// [Prueba28] Sobre el listado de ofertas de un usuario con menos de 20 euros de
//	// saldo, pinchar en el
//	// enlace Destacada y a continuaciÃ³n comprobar que se muestra el mensaje de
//	// saldo no suficiente.
//	@Test
//	public void PR28() {
//		PO_HomeView.clickOption(driver, "/identificarse", "class", "signup");
//		// Rellenamos el formulario con admin
//		PO_LoginView.fillForm(driver, "prueba@prueba.com", "123456");
//		// Click en usuarios
//
//		List<WebElement> elementos = PO_View.checkElement(driver, "free", "//li[contains(@id, 'offers-menu')]/a");
//		elementos.get(0).click();
//
//		// Pinchamos en la opcioÌ�n de lista de ofertas.
//		elementos = PO_View.checkElement(driver, "free", "//a[contains(@href,'offer/mylist')]");
//		elementos.get(0).click();
//		List<WebElement> elementos1 = SeleniumUtils.EsperaCargaPagina(driver, "free", "//tbody/tr",
//				PO_View.getTimeout());
//
//		elementos = PO_View.checkElement(driver, "free",
//				"//td[contains(text(), 'OPEL19')]/following-sibling::*/a[contains(@href, 'offer/special')]");
//		elementos.get(0).click();
//
//		PO_View.checkElement(driver, "text", "No se ha realizado la compra");
//		// Salimos de sesion
//		PO_PrivateView.clickOption(driver, "/desconectarse", "class", "btn btn-primary");
//
//	}

	//TODO
	//PRUEBAS Parte 2B – Cliente REST – Aplicación web con JQuery
	
//	[Prueba29] Inicio de sesión con datos válidos.
//	[Prueba30] Inicio de sesión con datos inválidos (email existente, pero contraseña incorrecta).
//	[Prueba31] Inicio de sesión con datos válidos (campo email o contraseña vacíos).
//	[Prueba32] Mostrar el listado de ofertas disponibles y comprobar que se muestran todas las que existen,
//	menos las del usuario identificado.
	
	
	// [Prueba29] Inicio de sesión con datos válidos.
		@Test
		public void PR29() {
			driver.navigate().to("http://localhost:8081/cliente.html");
			
			List<WebElement> elementos = PO_View.checkElement(driver, "free", "//*[@id=\"email\"]");
			elementos.get(0).click();
			elementos.get(0).clear();
			elementos.get(0).sendKeys("12@prueba.com");
			elementos = PO_View.checkElement(driver, "free", "//*[@id=\"password\"]");
			elementos.get(0).click();
			elementos.get(0).clear();
			elementos.get(0).sendKeys("12");
			elementos = PO_View.checkElement(driver, "free", "//*[@id=\"boton-login\"]");
			elementos.get(0).click();
			SeleniumUtils.EsperaCargaPagina(driver, "text", "titulodeofertadeprueba2222", PO_View.getTimeout());

		}

		// [Prueba30] Inicio de sesión con datos inválidos (email existente, pero contraseña incorrecta).
		@Test
		public void PR30() {
			driver.navigate().to("http://localhost:8081/cliente.html");
			
			List<WebElement> elementos = PO_View.checkElement(driver, "free", "//*[@id=\"email\"]");
			elementos.get(0).click();
			elementos.get(0).clear();
			elementos.get(0).sendKeys("12@prueba.com");
			elementos = PO_View.checkElement(driver, "free", "//*[@id=\"password\"]");
			elementos.get(0).click();
			elementos.get(0).clear();
			elementos.get(0).sendKeys("a");
			elementos = PO_View.checkElement(driver, "free", "//*[@id=\"boton-login\"]");
			elementos.get(0).click();
			SeleniumUtils.EsperaCargaPagina(driver, "text", "Usuario no encontrado", PO_View.getTimeout());


		}
		// [Prueba31] Inicio de sesión con datos válidos (campo email o contraseña vacíos).
		@Test
		public void PR31() {
			driver.navigate().to("http://localhost:8081/cliente.html");
			
			List<WebElement> elementos = PO_View.checkElement(driver, "free", "//*[@id=\"email\"]");
			elementos.get(0).click();
			elementos.get(0).clear();
			elementos.get(0).sendKeys("");
			elementos = PO_View.checkElement(driver, "free", "//*[@id=\"password\"]");
			elementos.get(0).click();
			elementos.get(0).clear();
			elementos.get(0).sendKeys("a");
			elementos = PO_View.checkElement(driver, "free", "//*[@id=\"boton-login\"]");
			elementos.get(0).click();
			SeleniumUtils.EsperaCargaPagina(driver, "text", "Usuario no encontrado", PO_View.getTimeout());


		}
		
		// [Prueba32] Mostrar el listado de ofertas disponibles y comprobar que se muestran todas las que existen,
//		menos las del usuario identificado.
		@Test
		public void PR32() {
			//Creamos nueva oferta y comprobamos que no sale
			PO_HomeView.clickOption(driver, "/identificarse", "class", "signup");
			PO_LoginView.fillForm(driver, "12@prueba.com", "12");
			driver.navigate().to("http://localhost:8081/productos/agregar");
			PO_AddOffer.fillForm(driver, "ofertaquenosale", "ofertaquenosale", "100");
			PO_PrivateView.clickOption(driver, "/desconectarse", "class", "btn btn-primary");
			// Vamos al formulario de logueo.
			
			// Rellenamos el formulario con admin
			
			driver.navigate().to("http://localhost:8081/cliente.html");
			
			List<WebElement> elementos = PO_View.checkElement(driver, "free", "//*[@id=\"email\"]");
			elementos.get(0).click();
			elementos.get(0).clear();
			elementos.get(0).sendKeys("12@prueba.com");
			elementos = PO_View.checkElement(driver, "free", "//*[@id=\"password\"]");
			elementos.get(0).click();
			elementos.get(0).clear();
			elementos.get(0).sendKeys("12");
			elementos = PO_View.checkElement(driver, "free", "//*[@id=\"boton-login\"]");
			elementos.get(0).click();
			SeleniumUtils.EsperaCargaPaginaNoTexto(driver, "ofertaquenosale", PO_View.getTimeout());


		}
		
		// [Prueba33] Sobre una búsqueda determinada de ofertas (a elección de desarrollador), 
		//enviar un mensaje a una oferta concreta. 
		//Se abriría dicha conversación por primera vez. Comprobar que el mensaje aparece en el listado de mensajes.
		@Test
		public void PR33() {
			//TODO


		}
		
		// [Prueba34] Sobre el listado de conversaciones enviar un mensaje a una conversación ya abierta. 
		//Comprobar que el mensaje aparece en el listado de mensajes.
		@Test
		public void PR34() {
			//TODO


		}

		

//	// [Prueba28] Intentar acceder sin estar autenticado a la opciÃ³n de listado de
//	// usuarios del administrador. Se
//	// deberÃ¡ volver al formulario de login.
//	@Test
//	public void PR28() {
//		driver.navigate().to(URL + "/user/list");
//		PO_View.checkElement(driver, "text", "Email");
//	}

//	// [Prueba29] Intentar acceder sin estar autenticado a la opciÃ³n de listado de
//	// ofertas propias de un usuario
//	// estÃ¡ndar. Se deberÃ¡ volver al formulario de login.
//	@Test
//	public void PR29() {
//		driver.navigate().to(URL + "/offer/mylist");
//		PO_View.checkElement(driver, "text", "Email");
//	}

//	// [Prueba30] Estando autenticado como usuario estÃ¡ndar intentar acceder a la
//	// opciÃ³n de listado de
//	// usuarios del administrador. Se deberÃ¡ indicar un mensaje de acciÃ³n prohibida
//
//	public void PR30() {
//
//		// Vamos al formulario de logueo.
//		PO_HomeView.clickOption(driver, "/identificarse", "class", "signup");
//		// Rellenamos el formulario con admin
//		PO_LoginView.fillForm(driver, "prueba@prueba.com", "123456");
//		// Click en usuarios
//		driver.navigate().to(URL + "/user/list");
//		PO_View.checkElement(driver, "text", "403");
//		// Salimos de sesion
//		PO_PrivateView.clickOption(driver, "/desconectarse", "class", "btn btn-primary");
//
//
//	}

	

	// [Prueba35] Mostrar el listado de conversaciones ya abiertas. Comprobar que el
	// listado contiene las
	// conversaciones que deben ser.
	@Test
	public void PR35() {
		// Vamos al formulario de logueo.
		PO_HomeView.clickOption(driver, "/identificarse", "class", "signup");
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
		PO_PrivateView.clickOption(driver, "/desconectarse", "class", "btn btn-primary");

	}

	// [Prueba36] Sobre el listado de conversaciones ya abiertas. Pinchar el enlace
	// Eliminar de la primera y
	// comprobar que el listado se actualiza correctamente.
	@Test
	public void PR36() {
		// Vamos al formulario de logueo.
		PO_HomeView.clickOption(driver, "/identificarse", "class", "signup");
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
		PO_PrivateView.clickOption(driver, "/desconectarse", "class", "btn btn-primary");

	}

	// [Prueba37] Sobre el listado de conversaciones ya abiertas. Pinchar el enlace
	// Eliminar de la Ãºltima y
	// comprobar que el listado se actualiza correctamente.
	@Test
	public void PR37() {
		// Vamos al formulario de logueo.
		PO_HomeView.clickOption(driver, "/identificarse", "class", "signup");
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
		PO_PrivateView.clickOption(driver, "/desconectarse", "class", "btn btn-primary");

		// Vamos al formulario de logueo.
		PO_HomeView.clickOption(driver, "/identificarse", "class", "signup");
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
		PO_PrivateView.clickOption(driver, "/desconectarse", "class", "btn btn-primary");

	}
	
	//TODO
		//OPCIONALES CONVERSACION
//		[Prueba38] Identificarse en la aplicación y enviar un mensaje a una oferta, validar que el mensaje enviado
//		aparece en el chat. Identificarse después con el usuario propietario de la oferta y validar que tiene un
//		mensaje sin leer, entrar en el chat y comprobar que el mensaje pasa a tener el estado leído
//		[Prueba39] Identificarse en la aplicación y enviar tres mensajes a una oferta, validar que los mensajes
//		enviados aparecen en el chat. Identificarse después con el usuario propietario de la oferta y validar que el
//		número de mensajes sin leer aparece en la en su oferta.


	//TODO Añadir lo de rehacer la bbdd
	// Al finalizar la uÌ�ltima prueba
	@AfterClass
	static public void end() {
		// Cerramos el navegador al finalizar las pruebas
		driver.quit();
	}

}
