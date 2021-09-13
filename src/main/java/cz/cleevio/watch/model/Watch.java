package cz.cleevio.watch.model;

import javax.persistence.*;
import javax.validation.constraints.*;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement
public class Watch {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Title is mandatory")
    @Size(min = 3, max = 15, message = "Description should have min 3 and max 15 characters.")
    private String title;

    @NotNull(message = "Price is mandatory")
    @PositiveOrZero(message = "Price should be positive or zero")
    private Integer price;

    @NotNull(message = "Description is mandatory")
    @Size(min = 3, max = 500, message = "Description should have min 3 and max 500 characters.")
    private String description;

    @Lob
    @Basic(fetch = FetchType.LAZY)
    @NotNull(message = "Fountain image is mandatory")
    @Column(columnDefinition="BLOB")
    private byte[] fountain;

    public Watch() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public byte[] getFountain() {
        return fountain;
    }

    public void setFountain(byte[] image) {
        this.fountain = image;
    }
}
