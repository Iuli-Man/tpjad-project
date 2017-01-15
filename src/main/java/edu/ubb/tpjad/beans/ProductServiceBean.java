package edu.ubb.tpjad.beans;

import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import edu.ubb.tpjad.entity.Product;
import edu.ubb.tpjad.interfaces.ProductService;

@Stateless
@Remote(ProductService.class)
public class ProductServiceBean implements ProductService {

	@PersistenceContext(unitName = "tpjad-project")
	private EntityManager entityManager;

	@Override
	public List<Product> listProducts(Product p) {
		return entityManager
				.createQuery("SELECT p FROM Product p WHERE p.name LIKE :name AND p.type LIKE :type AND p.description LIKE :description", Product.class)
				.setParameter("name", "%" + p.getName() + "%")
				.setParameter("type", "%" + p.getType() + "%")
				.setParameter("description", "%" + p.getDescription() + "%")
				.getResultList();
	}

	@Override
	public Product add(Product p) {
		return entityManager.merge(p);
	}

}
