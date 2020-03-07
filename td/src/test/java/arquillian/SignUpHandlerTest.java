package arquillian;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Assert;
import org.junit.Test;

import handler.basichandler.SignUpHandler;

public class SignUpHandlerTest {
	@Deployment
	public static WebArchive createDeployment() {
		return ShrinkWrap.create(WebArchive.class).addClass(SignUpHandler.class)
				.addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml")
				.addAsResource("persistence.xml", "META-INF/persistence.xml");
	}

	@PersistenceContext
	EntityManager em;
	@Resource
	private UserTransaction utx;

	@EJB
	SignUpHandler signUpHandler;

	@Test
	public void test() {
		Assert.assertNotNull(signUpHandler);
		Assert.assertNotNull(signUpHandler.getEm());
		Assert.assertNotNull(signUpHandler);
	}
}