package arquillian;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Before;
import org.junit.runner.RunWith;

import user.Role;
import user.User;
import user.UserFactory;

@RunWith(Arquillian.class)
public class IntegrationTest {
	@PersistenceContext
	private EntityManager em;

	@Resource
	private UserTransaction userTransaction;

	private User user;

	@Deployment
	    public static WebArchive createDeployment()
	    {
	        return ShrinkWrap
	                .create(WebArchive.class)
	                .addClass(User.class)
	                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml")
	                .addAsResource("persistence.xml", "META-INF/persistence.xml");
	    }

	@Before
	public void setup() throws Exception {
		user = UserFactory.getInstance(Role.CUSTOMER).create("sasas","sasasa");
		userTransaction.begin();
		em.persist(user);
		userTransaction.commit();
	}
}