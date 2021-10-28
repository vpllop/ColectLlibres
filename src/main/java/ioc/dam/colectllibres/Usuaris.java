/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ioc.dam.colectllibres;

import java.io.Serializable;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import jakarta.xml.bind.annotation.XmlRootElement;

/**
 *
 * http://localhost:8080/hellojax/res/usuaris
 */
@Entity
@Table(name = "usuaris")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Usuaris.findAll", query = "SELECT u FROM Usuaris u"),
    @NamedQuery(name = "Usuaris.findByIdusuari", query = "SELECT u FROM Usuaris u WHERE u.idusuari = :idusuari"),
    @NamedQuery(name = "Usuaris.findByUsuari", query = "SELECT u FROM Usuaris u WHERE u.usuari = :usuari"),
    @NamedQuery(name = "Usuaris.findByPass", query = "SELECT u FROM Usuaris u WHERE u.pass = :pass"),
    @NamedQuery(name = "Usuaris.findByNom", query = "SELECT u FROM Usuaris u WHERE u.nom = :nom"),
    @NamedQuery(name = "Usuaris.findByCognoms", query = "SELECT u FROM Usuaris u WHERE u.cognoms = :cognoms"),
    @NamedQuery(name = "Usuaris.findByDni", query = "SELECT u FROM Usuaris u WHERE u.dni = :dni"),
    @NamedQuery(name = "Usuaris.findByTelefon", query = "SELECT u FROM Usuaris u WHERE u.telefon = :telefon"),
    @NamedQuery(name = "Usuaris.findByAdmin", query = "SELECT u FROM Usuaris u WHERE u.admin = :admin")})

public class Usuaris implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idusuari")
    private Short idusuari;
    @Column(name = "usuari")
    private String usuari;
    @Column(name = "pass")
    private String pass;
    @Basic(optional = false)
    @Column(name = "nom")
    private String nom;
    @Column(name = "cognoms")
    private String cognoms;
    @Column(name = "dni")
    private String dni;
    @Column(name = "telefon")
    private String telefon;
   @Column(name = "admin")
    private String admin;

    public Usuaris() {
    }

    public Usuaris(Short idusuari) {
        this.idusuari = idusuari;
    }

    public Usuaris(Short idusuari, String nom) {
        this.idusuari = idusuari;
        this.nom = nom;
    }

    public Short getIdusuari() {
        return idusuari;
    }

    public void setIdusuari(Short idusuari) {
        this.idusuari = idusuari;
    }

    public String getUsuari() {
        return usuari;
    }

    public void setUsuari(String usuari) {
        this.usuari = usuari;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getCognoms() {
        return cognoms;
    }

    public void setCognoms(String cognoms) {
        this.cognoms = cognoms;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idusuari != null ? idusuari.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Usuaris)) {
            return false;
        }
        Usuaris other = (Usuaris) object;
        if ((this.idusuari == null && other.idusuari != null) || (this.idusuari != null && !this.idusuari.equals(other.idusuari))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Usuaris[ idusuari=" + idusuari + " ]";
    }

    public String getAdmin() {
        return admin;
    }

    public void setAdmin(String admin) {
        this.admin = admin;
    }
    
}
