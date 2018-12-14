package it.ltc.database.model.legacy;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the Ubicazioni database table.
 * 
 */
@Entity
@Table(name="Ubicazioni")
//@NamedQuery(name="Ubicazioni.findAll", query="SELECT u FROM Ubicazioni u")
public class Ubicazioni implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="IdUbicazioni", unique=true, nullable=false)
	private int idUbicazioni;

//	@Column(name="Altezza")
//	private int altezza;

	@Column(name="Area", nullable=false, length=2, columnDefinition="char(2)")
	private String area;

	@Column(name="Box", nullable=false, length=2, columnDefinition="char(2)")
	private String box;

//	@Column(name="Chiave1", length=50)
//	private String chiave1;

//	@Column(name="CodBarreUbica", length=20)
//	private String codBarreUbica;

	@Column(name="Corsia", nullable=false, length=3, columnDefinition="char(3)")
	private String corsia;

//	@Column(name="DesUbica", length=20)
//	private String desUbica;

//	@Column(name="IdCliAttAsso")
//	private int idCliAttAsso;

//	@Column(name="IdDestina")
//	private int idDestina;

//	@Column(name="Int1", length=1)
//	private String int1;
//
//	@Column(name="Int2", length=1)
//	private String int2;
//
//	@Column(name="Int3", length=1)
//	private String int3;
//
//	@Column(name="Int4", length=1)
//	private String int4;

//	@Column(name="KeyCoordinata", length=20)
//	private String keyCoordinata;

//	@Column(name="KeyMappa", length=10)
//	private String keyMappa;

	@Column(name="KeyUbica", nullable=false, length=15, columnDefinition="char(15)")
	private String keyUbica;

//	@Column(name="Larghezza")
//	private int larghezza;

	@Column(name="Magazzino", nullable=false, length=3, columnDefinition="char(3)")
	private String magazzino;

//	@Column(name="MaxPeso", nullable=false)
//	private int maxPeso;

//	@Column(name="MemArticolo")
//	private short memArticolo;

	@Column(name="Piano", nullable=false, length=2, columnDefinition="char(2)")
	private String piano;

//	@Column(name="Profondità")
//	private int profondità;

	@Column(name="Scaffale", nullable=false, length=3, columnDefinition="char(3)")
	private String scaffale;

//	@Column(name="Stato", length=2)
//	private String stato;

	@Column(name="TipoUbica", length=2, nullable=false, columnDefinition="char(2)")
	private String tipoUbica;

//	@Column(name="TipoUtilizzo", length=2)
//	private String tipoUtilizzo;

//	@Column(name="Volume")
//	private int volume;
//
//	@Column(name="X")
//	private int x;
//
//	@Column(name="Y")
//	private int y;

	public Ubicazioni() {}

	public int getIdUbicazioni() {
		return this.idUbicazioni;
	}

	public void setIdUbicazioni(int idUbicazioni) {
		this.idUbicazioni = idUbicazioni;
	}

//	public int getAltezza() {
//		return this.altezza;
//	}
//
//	public void setAltezza(int altezza) {
//		this.altezza = altezza;
//	}

	public String getArea() {
		return this.area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getBox() {
		return this.box;
	}

	public void setBox(String box) {
		this.box = box;
	}

//	public String getChiave1() {
//		return this.chiave1;
//	}
//
//	public void setChiave1(String chiave1) {
//		this.chiave1 = chiave1;
//	}
//
//	public String getCodBarreUbica() {
//		return this.codBarreUbica;
//	}
//
//	public void setCodBarreUbica(String codBarreUbica) {
//		this.codBarreUbica = codBarreUbica;
//	}

	public String getCorsia() {
		return this.corsia;
	}

	public void setCorsia(String corsia) {
		this.corsia = corsia;
	}

//	public String getDesUbica() {
//		return this.desUbica;
//	}
//
//	public void setDesUbica(String desUbica) {
//		this.desUbica = desUbica;
//	}
//
//	public int getIdCliAttAsso() {
//		return this.idCliAttAsso;
//	}
//
//	public void setIdCliAttAsso(int idCliAttAsso) {
//		this.idCliAttAsso = idCliAttAsso;
//	}
//
//	public int getIdDestina() {
//		return this.idDestina;
//	}
//
//	public void setIdDestina(int idDestina) {
//		this.idDestina = idDestina;
//	}
//
//	public String getInt1() {
//		return this.int1;
//	}
//
//	public void setInt1(String int1) {
//		this.int1 = int1;
//	}
//
//	public String getInt2() {
//		return this.int2;
//	}
//
//	public void setInt2(String int2) {
//		this.int2 = int2;
//	}
//
//	public String getInt3() {
//		return this.int3;
//	}
//
//	public void setInt3(String int3) {
//		this.int3 = int3;
//	}
//
//	public String getInt4() {
//		return this.int4;
//	}
//
//	public void setInt4(String int4) {
//		this.int4 = int4;
//	}
//
//	public String getKeyCoordinata() {
//		return this.keyCoordinata;
//	}
//
//	public void setKeyCoordinata(String keyCoordinata) {
//		this.keyCoordinata = keyCoordinata;
//	}
//
//	public String getKeyMappa() {
//		return this.keyMappa;
//	}
//
//	public void setKeyMappa(String keyMappa) {
//		this.keyMappa = keyMappa;
//	}

	public String getKeyUbica() {
		return this.keyUbica;
	}

	public void setKeyUbica(String keyUbica) {
		this.keyUbica = keyUbica;
	}

//	public int getLarghezza() {
//		return this.larghezza;
//	}
//
//	public void setLarghezza(int larghezza) {
//		this.larghezza = larghezza;
//	}

	public String getMagazzino() {
		return this.magazzino;
	}

	public void setMagazzino(String magazzino) {
		this.magazzino = magazzino;
	}

//	public int getMaxPeso() {
//		return this.maxPeso;
//	}
//
//	public void setMaxPeso(int maxPeso) {
//		this.maxPeso = maxPeso;
//	}
//
//	public short getMemArticolo() {
//		return this.memArticolo;
//	}
//
//	public void setMemArticolo(short memArticolo) {
//		this.memArticolo = memArticolo;
//	}

	public String getPiano() {
		return this.piano;
	}

	public void setPiano(String piano) {
		this.piano = piano;
	}

//	public int getProfondità() {
//		return this.profondità;
//	}
//
//	public void setProfondità(int profondità) {
//		this.profondità = profondità;
//	}

	public String getScaffale() {
		return this.scaffale;
	}

	public void setScaffale(String scaffale) {
		this.scaffale = scaffale;
	}

//	public String getStato() {
//		return this.stato;
//	}
//
//	public void setStato(String stato) {
//		this.stato = stato;
//	}

	public String getTipoUbica() {
		return this.tipoUbica;
	}

	public void setTipoUbica(String tipoUbica) {
		this.tipoUbica = tipoUbica;
	}

//	public String getTipoUtilizzo() {
//		return this.tipoUtilizzo;
//	}
//
//	public void setTipoUtilizzo(String tipoUtilizzo) {
//		this.tipoUtilizzo = tipoUtilizzo;
//	}
//
//	public int getVolume() {
//		return this.volume;
//	}
//
//	public void setVolume(int volume) {
//		this.volume = volume;
//	}
//
//	public int getX() {
//		return this.x;
//	}
//
//	public void setX(int x) {
//		this.x = x;
//	}
//
//	public int getY() {
//		return this.y;
//	}
//
//	public void setY(int y) {
//		this.y = y;
//	}

}