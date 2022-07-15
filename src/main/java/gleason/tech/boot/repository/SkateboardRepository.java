package gleason.tech.boot.repository;

import gleason.tech.boot.model.Skateboard;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SkateboardRepository extends JpaRepository<Skateboard, UUID> {
}
