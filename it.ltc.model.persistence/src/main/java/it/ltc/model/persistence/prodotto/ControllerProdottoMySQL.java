package it.ltc.model.persistence.prodotto;

import it.ltc.database.dao.Dao;
import it.ltc.database.model.centrale.Commessa;
import it.ltc.model.interfaces.exception.ModelPersistenceException;
import it.ltc.model.interfaces.exception.ModelValidationException;
import it.ltc.model.interfaces.model.IControllerModel;
import it.ltc.model.interfaces.prodotto.MProdotto;

public class ControllerProdottoMySQL extends Dao implements IControllerModel<MProdotto> {
	
//	private final Commessa cliente;
//	private final EntityManager<Prodotto> managerProdotti;
//	private final EntityManager<Brand> managerBrand;
//	private final List<Brand> brands;
	
	public ControllerProdottoMySQL(Commessa c) {
		super(c.getNomeRisorsa());
//		managerProdotti = new EntityManager<Prodotto>(Prodotto.class, db);
//		managerBrand = new EntityManager<Brand>(Brand.class, db);
//		brands = managerBrand.getEntities();
//		cliente = c;
	}
	
//	public List<MProdotto> getProdotti() {
//		Prodotto filtro = new Prodotto();
//		filtro.setIdCliente(cliente.getIdCliente());
//		List<Prodotto> listaProdottiMySQL = managerProdotti.getEntities(filtro);
//		ArrayList<MProdotto> prodotti = new ArrayList<MProdotto>();
//		for (Prodotto prodottoMySQL : listaProdottiMySQL) {
//			MProdotto prodotto = new MProdotto();
//			prodotto.setBarcode(prodottoMySQL.getBarcode());
//			prodotto.setBrand(getBrand(prodottoMySQL.getIdBrand()));
//			prodotto.setCategoria(Categoria.getCategoriaPerID(prodottoMySQL.getIdCategoria()).getCategoria());
//			prodotto.setChiavecliente(prodottoMySQL.getCodificaCliente());
//			prodotto.setCodicemodello(prodottoMySQL.getCodiceModello());
//			prodotto.setColore(prodottoMySQL.getColore());
//			prodotto.setComposizione(prodottoMySQL.getComposizione());
//			prodotto.setDescrizione(prodottoMySQL.getDescrizione());
//			prodotto.setH(prodottoMySQL.getH());
//			prodotto.setL(prodottoMySQL.getL());
//			prodotto.setZ(prodottoMySQL.getZ());
//			prodotto.setMadein(prodottoMySQL.getMadeIn());
//			prodotto.setPeso(prodottoMySQL.getPeso());
//			prodotto.setSottocategoria(prodottoMySQL.getSottoCategoria());
//			prodotto.setTaglia(prodottoMySQL.getTaglia());
//			prodotto.setValore(prodottoMySQL.getValore());
//			prodotti.add(prodotto);
//		}
//		return prodotti;
//	}

	@Override
	public MProdotto inserisci(MProdotto prodotto) throws ModelPersistenceException {
//		Prodotto product = new Prodotto();
//		//Copio le informazioni
//		product.setBarcode(prodotto.getBarcode());
//		product.setCodiceModello(prodotto.getCodicemodello());
//		product.setCodificaCliente(prodotto.getChiavecliente());
//		product.setColore(prodotto.getColore());
//		product.setComposizione(prodotto.getComposizione());
//		product.setDescrizione(prodotto.getDescrizione());
//		product.setIdBrand(getBrand(prodotto));
//		product.setIdCategoria(getCategoria(prodotto));
//		product.setIdCliente(cliente.getIdCliente());
//		product.setMadeIn(prodotto.getMadein());
//		product.setPeso(prodotto.getPeso());
//		product.setSottoCategoria(prodotto.getSottocategoria());
//		product.setTaglia(prodotto.getTaglia());
//		product.setValore(prodotto.getValore());
//		product.setH(prodotto.getH());
//		product.setL(prodotto.getL());
//		product.setZ(prodotto.getZ());
//		//Vado in scrittura sul DB
//		int id = managerProdotti.insertAndReturnID(product);
//		if (id == -1)
//			throw new ModelPersistenceException("L'inserimento del prodotto è fallito. Se il problema persiste contattare il reparto IT.");
//		return id;
		return null; //FIXME
	}

	@Override
	public void valida(MProdotto prodotto) throws ModelValidationException {
//		//Controllo sulla chiave cliente
//		Prodotto filtroChiaveCliente = new Prodotto();
//		filtroChiaveCliente.setIdCliente(cliente.getId());
//		filtroChiaveCliente.setCodificaCliente(prodotto.getChiavecliente());
//		Prodotto trovataChiave = managerProdotti.getEntity(filtroChiaveCliente);
//		if (trovataChiave != null) {
//			throw new ModelValidationException("E' gia' presente un prodotto con la stessa chiave cliente.");
//		}
//		//Controllo sul codice modello e taglia
//		Prodotto filtroModelloTaglia = new Prodotto();
//		filtroModelloTaglia.setIdCliente(cliente.getId());
//		filtroModelloTaglia.setCodiceModello(prodotto.getCodicemodello());
//		filtroModelloTaglia.setTaglia(prodotto.getTaglia());
//		Prodotto trovatoCodiceTaglia = managerProdotti.getEntity(filtroModelloTaglia);
//		if (trovatoCodiceTaglia != null) {
//			throw new ModelValidationException("E' gia' presente un prodotto con la stessa combinazione codice modello-taglia.");
//		}
//		//Controllo barcode
//		Prodotto filtroBarcode = new Prodotto();
//		filtroBarcode.setIdCliente(cliente.getId());
//		filtroBarcode.setBarcode(prodotto.getBarcode());
//		Prodotto trovatoBarcode = managerProdotti.getEntity(filtroBarcode);
//		if (trovatoBarcode != null) {
//			throw new ModelValidationException("E' gia' presente un prodotto con lo stesso barcode.");
//		}
	}
	
//	private String getBrand(Integer idBrand) {
//		String nomeBrand = "";
//		for (Brand brand : brands) {
//			if (brand.getId().equals(idBrand)) {
//				nomeBrand = brand.getNome();
//				break;
//			}
//		}
//		return nomeBrand;
//	}
	
//	private Integer getBrand(MProdotto prodotto) {
//		Integer id = null;
//		String nomeBrand = prodotto.getBrand();
//		for (Brand brand : brands) {
//			if (brand.getNome().equals(nomeBrand)) {
//				id = brand.getId();
//				break;
//			}
//		}
//		//Se non è stato provato provvedo ad inserirlo.
//		if (id == null) {
//			Brand nuovoBrand = new Brand();
//			nuovoBrand.setNome(nomeBrand);
//			id = managerBrand.insertAndReturnID(nuovoBrand);
//			if (id != -1) {
//				nuovoBrand.setId(id);
//				brands.add(nuovoBrand);
//			}
//		}
//		return id;
//	}
	
//	private Integer getCategoria(MProdotto prodotto) {
//		Categoria categoria = Categoria.valueOf(prodotto.getCategoria());
//		int id = categoria.getID();
//		return id;
//	}

	@Override
	public MProdotto modifica(MProdotto model) throws ModelPersistenceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MProdotto elimina(MProdotto model) throws ModelPersistenceException {
		// TODO Auto-generated method stub
		return null;
	}

}
