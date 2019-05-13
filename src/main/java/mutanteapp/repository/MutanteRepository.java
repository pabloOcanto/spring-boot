package mutanteapp.repository;

import mutanteapp.entity.MutanteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Pablo on 10/5/2019.
 */
@Repository
public interface MutanteRepository extends JpaRepository<MutanteEntity,Long> {
    Long countByStatus(Boolean status);
}
