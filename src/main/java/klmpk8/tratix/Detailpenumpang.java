/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package klmpk8.tratix;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author HP
 */
@Entity
@Table(name = "detailpenumpang")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Detailpenumpang.findAll", query = "SELECT d FROM Detailpenumpang d"),
    @NamedQuery(name = "Detailpenumpang.findByNama", query = "SELECT d FROM Detailpenumpang d WHERE d.nama = :nama"),
    @NamedQuery(name = "Detailpenumpang.findById", query = "SELECT d FROM Detailpenumpang d WHERE d.id = :id"),
    @NamedQuery(name = "Detailpenumpang.findByGerbong", query = "SELECT d FROM Detailpenumpang d WHERE d.gerbong = :gerbong"),
    @NamedQuery(name = "Detailpenumpang.findByTanggal", query = "SELECT d FROM Detailpenumpang d WHERE d.tanggal = :tanggal")})
public class Detailpenumpang implements Serializable {

    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @Column(name = "nama")
    private String nama;
    @Id
    @Basic(optional = false)
    @Column(name = "id")
    private String id;
    @Basic(optional = false)
    @Column(name = "gerbong")
    private String gerbong;
    @Basic(optional = false)
    @Column(name = "tanggal")
    @Temporal(TemporalType.DATE)
    private Date tanggal;

    public Detailpenumpang() {
    }

    public Detailpenumpang(String id) {
        this.id = id;
    }

    public Detailpenumpang(String id, String nama, String gerbong, Date tanggal) {
        this.id = id;
        this.nama = nama;
        this.gerbong = gerbong;
        this.tanggal = tanggal;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGerbong() {
        return gerbong;
    }

    public void setGerbong(String gerbong) {
        this.gerbong = gerbong;
    }

    public Date getTanggal() {
        return tanggal;
    }

    public void setTanggal(Date tanggal) {
        this.tanggal = tanggal;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Detailpenumpang)) {
            return false;
        }
        Detailpenumpang other = (Detailpenumpang) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "klmpk8.tratix.Detailpenumpang[ id=" + id + " ]";
    }
    
}
