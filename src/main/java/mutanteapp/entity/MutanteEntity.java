package mutanteapp.entity;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Arrays;

/**
 * Created by Pablo on 10/5/2019.
 */

@Entity
@Table(name = "MUTANTES")
public class MutanteEntity {
    @Id
    @GeneratedValue
    long id;
    @Column
    private String[] secuence;
    @Column
    private boolean status;
    @Column(name = "created_at")
    private LocalDateTime date;//= LocalDateTime.now();

    public MutanteEntity() {
    }

    public MutanteEntity(String[] secuence, boolean status) {
        this.secuence = secuence;
        this.status = status;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String[] getSecuence() {
        return secuence;
    }

    public void setSecuence(String[] secuence) {
        this.secuence = secuence;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public LocalDateTime getDate() {
        return date;
    }

    @Override
    public String toString() {
        return "MutanteEntity{" +
                "id=" + id +
                ", secuence=" + Arrays.toString(secuence) +
                ", status=" + status +
                ", date=" + date +
                '}';
    }
}
