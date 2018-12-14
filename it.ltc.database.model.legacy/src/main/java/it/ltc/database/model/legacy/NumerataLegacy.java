package it.ltc.database.model.legacy;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * The persistent class for the Numerata database table.
 * 
 */
@Entity
@Table(name="Numerata")
//@NamedQuery(name="Numerata.findAll", query="SELECT n FROM Numerata n")
public class NumerataLegacy implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="IdNumerata", unique=true, nullable=false)
	private int idNumerata;

	@Column(name="Codice", length=20)
	private String codice;

	@Column(name="Taglia1", length=15)
	private String taglia1;

	@Column(name="Taglia10", length=15)
	private String taglia10;

	@Column(name="Taglia11", length=15)
	private String taglia11;

	@Column(name="Taglia12", length=15)
	private String taglia12;

	@Column(name="Taglia13", length=15)
	private String taglia13;

	@Column(name="Taglia14", length=15)
	private String taglia14;

	@Column(name="Taglia15", length=15)
	private String taglia15;

	@Column(name="Taglia16", length=15)
	private String taglia16;

	@Column(name="Taglia17", length=15)
	private String taglia17;

	@Column(name="Taglia18", length=15)
	private String taglia18;

	@Column(name="Taglia19", length=15)
	private String taglia19;

	@Column(name="Taglia2", length=15)
	private String taglia2;

	@Column(name="Taglia20", length=15)
	private String taglia20;

	@Column(name="Taglia21", length=15)
	private String taglia21;

	@Column(name="Taglia22", length=15)
	private String taglia22;

	@Column(name="Taglia23", length=15)
	private String taglia23;

	@Column(name="Taglia24", length=15)
	private String taglia24;

	@Column(name="Taglia25", length=15)
	private String taglia25;

	@Column(name="Taglia26", length=15)
	private String taglia26;

	@Column(name="Taglia27", length=15)
	private String taglia27;

	@Column(name="Taglia28", length=15)
	private String taglia28;

	@Column(name="Taglia29", length=15)
	private String taglia29;

	@Column(name="Taglia3", length=15)
	private String taglia3;

	@Column(name="Taglia30", length=15)
	private String taglia30;

	@Column(name="Taglia31", length=15)
	private String taglia31;

	@Column(name="Taglia32", length=15)
	private String taglia32;

	@Column(name="Taglia33", length=15)
	private String taglia33;

	@Column(name="Taglia34", length=15)
	private String taglia34;

	@Column(name="Taglia35", length=15)
	private String taglia35;

	@Column(name="Taglia36", length=15)
	private String taglia36;

	@Column(name="Taglia37", length=15)
	private String taglia37;

	@Column(name="Taglia38", length=15)
	private String taglia38;

	@Column(name="Taglia39", length=15)
	private String taglia39;

	@Column(name="Taglia4", length=15)
	private String taglia4;

	@Column(name="Taglia40", length=15)
	private String taglia40;

	@Column(name="Taglia5", length=15)
	private String taglia5;

	@Column(name="Taglia6", length=15)
	private String taglia6;

	@Column(name="Taglia7", length=15)
	private String taglia7;

	@Column(name="Taglia8", length=15)
	private String taglia8;

	@Column(name="Taglia9", length=15)
	private String taglia9;

	public NumerataLegacy() {}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idNumerata;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		NumerataLegacy other = (NumerataLegacy) obj;
		if (idNumerata != other.idNumerata)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Numerata legacy [idNumerata=" + idNumerata + ", codice=" + codice + "]";
	}

	public int getIdNumerata() {
		return this.idNumerata;
	}

	public void setIdNumerata(int idNumerata) {
		this.idNumerata = idNumerata;
	}

	public String getCodice() {
		return this.codice;
	}

	public void setCodice(String codice) {
		this.codice = codice;
	}

	public String getTaglia1() {
		return this.taglia1;
	}

	public void setTaglia1(String taglia1) {
		this.taglia1 = taglia1;
	}

	public String getTaglia10() {
		return this.taglia10;
	}

	public void setTaglia10(String taglia10) {
		this.taglia10 = taglia10;
	}

	public String getTaglia11() {
		return this.taglia11;
	}

	public void setTaglia11(String taglia11) {
		this.taglia11 = taglia11;
	}

	public String getTaglia12() {
		return this.taglia12;
	}

	public void setTaglia12(String taglia12) {
		this.taglia12 = taglia12;
	}

	public String getTaglia13() {
		return this.taglia13;
	}

	public void setTaglia13(String taglia13) {
		this.taglia13 = taglia13;
	}

	public String getTaglia14() {
		return this.taglia14;
	}

	public void setTaglia14(String taglia14) {
		this.taglia14 = taglia14;
	}

	public String getTaglia15() {
		return this.taglia15;
	}

	public void setTaglia15(String taglia15) {
		this.taglia15 = taglia15;
	}

	public String getTaglia16() {
		return this.taglia16;
	}

	public void setTaglia16(String taglia16) {
		this.taglia16 = taglia16;
	}

	public String getTaglia17() {
		return this.taglia17;
	}

	public void setTaglia17(String taglia17) {
		this.taglia17 = taglia17;
	}

	public String getTaglia18() {
		return this.taglia18;
	}

	public void setTaglia18(String taglia18) {
		this.taglia18 = taglia18;
	}

	public String getTaglia19() {
		return this.taglia19;
	}

	public void setTaglia19(String taglia19) {
		this.taglia19 = taglia19;
	}

	public String getTaglia2() {
		return this.taglia2;
	}

	public void setTaglia2(String taglia2) {
		this.taglia2 = taglia2;
	}

	public String getTaglia20() {
		return this.taglia20;
	}

	public void setTaglia20(String taglia20) {
		this.taglia20 = taglia20;
	}

	public String getTaglia21() {
		return this.taglia21;
	}

	public void setTaglia21(String taglia21) {
		this.taglia21 = taglia21;
	}

	public String getTaglia22() {
		return this.taglia22;
	}

	public void setTaglia22(String taglia22) {
		this.taglia22 = taglia22;
	}

	public String getTaglia23() {
		return this.taglia23;
	}

	public void setTaglia23(String taglia23) {
		this.taglia23 = taglia23;
	}

	public String getTaglia24() {
		return this.taglia24;
	}

	public void setTaglia24(String taglia24) {
		this.taglia24 = taglia24;
	}

	public String getTaglia25() {
		return this.taglia25;
	}

	public void setTaglia25(String taglia25) {
		this.taglia25 = taglia25;
	}

	public String getTaglia26() {
		return this.taglia26;
	}

	public void setTaglia26(String taglia26) {
		this.taglia26 = taglia26;
	}

	public String getTaglia27() {
		return this.taglia27;
	}

	public void setTaglia27(String taglia27) {
		this.taglia27 = taglia27;
	}

	public String getTaglia28() {
		return this.taglia28;
	}

	public void setTaglia28(String taglia28) {
		this.taglia28 = taglia28;
	}

	public String getTaglia29() {
		return this.taglia29;
	}

	public void setTaglia29(String taglia29) {
		this.taglia29 = taglia29;
	}

	public String getTaglia3() {
		return this.taglia3;
	}

	public void setTaglia3(String taglia3) {
		this.taglia3 = taglia3;
	}

	public String getTaglia30() {
		return this.taglia30;
	}

	public void setTaglia30(String taglia30) {
		this.taglia30 = taglia30;
	}

	public String getTaglia31() {
		return this.taglia31;
	}

	public void setTaglia31(String taglia31) {
		this.taglia31 = taglia31;
	}

	public String getTaglia32() {
		return this.taglia32;
	}

	public void setTaglia32(String taglia32) {
		this.taglia32 = taglia32;
	}

	public String getTaglia33() {
		return this.taglia33;
	}

	public void setTaglia33(String taglia33) {
		this.taglia33 = taglia33;
	}

	public String getTaglia34() {
		return this.taglia34;
	}

	public void setTaglia34(String taglia34) {
		this.taglia34 = taglia34;
	}

	public String getTaglia35() {
		return this.taglia35;
	}

	public void setTaglia35(String taglia35) {
		this.taglia35 = taglia35;
	}

	public String getTaglia36() {
		return this.taglia36;
	}

	public void setTaglia36(String taglia36) {
		this.taglia36 = taglia36;
	}

	public String getTaglia37() {
		return this.taglia37;
	}

	public void setTaglia37(String taglia37) {
		this.taglia37 = taglia37;
	}

	public String getTaglia38() {
		return this.taglia38;
	}

	public void setTaglia38(String taglia38) {
		this.taglia38 = taglia38;
	}

	public String getTaglia39() {
		return this.taglia39;
	}

	public void setTaglia39(String taglia39) {
		this.taglia39 = taglia39;
	}

	public String getTaglia4() {
		return this.taglia4;
	}

	public void setTaglia4(String taglia4) {
		this.taglia4 = taglia4;
	}

	public String getTaglia40() {
		return this.taglia40;
	}

	public void setTaglia40(String taglia40) {
		this.taglia40 = taglia40;
	}

	public String getTaglia5() {
		return this.taglia5;
	}

	public void setTaglia5(String taglia5) {
		this.taglia5 = taglia5;
	}

	public String getTaglia6() {
		return this.taglia6;
	}

	public void setTaglia6(String taglia6) {
		this.taglia6 = taglia6;
	}

	public String getTaglia7() {
		return this.taglia7;
	}

	public void setTaglia7(String taglia7) {
		this.taglia7 = taglia7;
	}

	public String getTaglia8() {
		return this.taglia8;
	}

	public void setTaglia8(String taglia8) {
		this.taglia8 = taglia8;
	}

	public String getTaglia9() {
		return this.taglia9;
	}

	public void setTaglia9(String taglia9) {
		this.taglia9 = taglia9;
	}

}