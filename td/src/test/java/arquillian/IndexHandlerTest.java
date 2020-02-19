package arquillian;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.core.api.annotation.Inject;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import handler.basichandler.IndexHandler;

public class IndexHandlerTest {

	@Deployment
	public static WebArchive createDeployment() {
		return ShrinkWrap.create(WebArchive.class).addAsManifestResource("beans.xml", "beans.xml")
				.addAsWebInfResource("web.xml", "web.xml    ").addClass(IndexHandler.class);
	}

	@Before
	public void init() {
		indexHandler = new IndexHandler();
	}

	@Inject
	IndexHandler indexHandler;

	@Test
	public void login_page() {
		Assert.assertEquals(indexHandler.logIn(), "/login.xhtml");
	}

	@Test
	public void sigup_page() {
		Assert.assertEquals(indexHandler.signUp(), "/signup.xhtml");
	}
}