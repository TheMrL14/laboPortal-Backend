package be.ehb.finalwork.lennert.lapoportal.core.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.ToString;
import net.minidev.json.annotate.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.Positive;

@Entity
@JsonIgnoreProperties(value = {"sop"})
public @Data
class Step extends BaseEntity {

    @Positive
    private Integer stepNr;

    @Lob
    @Column(length = 100000)
    private String message;

    @ManyToOne
    @JoinColumn(name = "sop_id")
    @JsonIgnore
    @ToString.Exclude
    private SOP sop;

    @Column(name = "step_image_name", nullable = true)
    @ToString.Exclude
    private String imageName;

    @Lob
    @JsonIgnore
    @Column(name = "step_image", nullable = true, columnDefinition = "mediumblob")
    @ToString.Exclude
    private byte[] image;

    public Step() {
    }

    public Step(String message) {
        this.message = message.trim();
    }
}
