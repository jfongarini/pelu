package com.figaro.repository;

import java.util.List;

import org.hibernate.query.Query;

import com.figaro.model.Categoria;
import com.figaro.model.Ciudad;
import com.figaro.model.Peluquero;
import com.figaro.model.Trabajo;

@SuppressWarnings("unchecked")
public class ConfiguracionRepository extends AbstractRepository {

	public Integer saveCiudad (Ciudad ciudad) {
		return (Integer) getCurrentSession().save(ciudad); 
	}

	public void deleteCiudad(Integer idCiudad) {
		Ciudad toDelte = getCurrentSession().load(Ciudad.class, idCiudad);
		getCurrentSession().delete(toDelte);
	}
	
	public List<Ciudad> getCiudades() {
		return getCurrentSession().createQuery("from Ciudad").list();
	}
	
	public Integer saveTrabajo(Trabajo trabajo) {
		return (Integer) getCurrentSession().save("Trabajo",trabajo);
	}

	public void deleteTrabajo(Integer idTrabajo) {
		Trabajo toDelte = (Trabajo) getCurrentSession().get("Trabajo", idTrabajo);
		getCurrentSession().delete(toDelte);
	}
	
	public List<Trabajo> getTrabajos() {
		return getCurrentSession().createQuery("from Trabajo").list();
	}
	
	public Trabajo getTrabajo(Integer idTrabajo) {
		return (Trabajo) getCurrentSession().get("Trabajo", idTrabajo);
	}
	
	public List<Trabajo> buscar(String search) {
		Query<Trabajo> query = getCurrentSession().createQuery("FROM Trabajo t WHERE t.descripcion LIKE CONCAT('%',:search,'%')");
		query.setParameter("search", search);
	    return query.getResultList();
	}

	public Integer savePeluquero(Peluquero peluquero) {
		return (Integer) getCurrentSession().save(peluquero);
	}

	public void deletePeluquero(Integer idPeluquero) {
		Peluquero toDelte = getCurrentSession().load(Peluquero.class, idPeluquero);
		getCurrentSession().delete(toDelte);
	}

	public List<Peluquero> getPeluqueros() {
		return getCurrentSession().createQuery("from Peluquero").list();
	}

	public List<Categoria> getCategorias() {
		return getCurrentSession().createQuery("from Categoria").list();
	}

	public Integer saveCategoria(Categoria categoria) {
		return (Integer) getCurrentSession().save(categoria);
	}

	public void deleteCategoria(Integer idCategoria) {
		Categoria toDelte = getCurrentSession().load(Categoria.class, idCategoria);
		getCurrentSession().delete(toDelte);
	}


}