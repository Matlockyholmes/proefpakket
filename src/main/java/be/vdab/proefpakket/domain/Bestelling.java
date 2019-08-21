package be.vdab.proefpakket.domain;

import be.vdab.proefpakket.adapters.LocalDateAdapter;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "bestellingen")
@XmlAccessorType(XmlAccessType.FIELD)
public class Bestelling implements Serializable {
    public interface Stap1{}
    public interface Stap2{}
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @XmlJavaTypeAdapter(value = LocalDateAdapter.class)
    private LocalDate datum = LocalDate.now();
    @NotBlank(groups = Stap1.class)
    private String voornaam;
    @NotBlank(groups = Stap1.class)
    private String familienaam;
    @NotBlank(groups = Stap1.class)
    @Email(groups = Stap1.class)
    private String emailAdres;
    @Valid
    @Embedded
    private Adres adres;
    @NotNull
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "brouwerid")
    private Brouwer brouwer;

    public long getId() {
        return id;
    }

    public String getVoornaam() {
        return voornaam;
    }

    public String getFamilienaam() {
        return familienaam;
    }

    public String getEmailAdres() {
        return emailAdres;
    }

    public Adres getAdres() {
        return adres;
    }

    public Brouwer getBrouwer() {
        return brouwer;
    }
}
