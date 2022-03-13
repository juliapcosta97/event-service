package com.hype.eventservice.api.event.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigInteger;
import java.util.List;

public interface EventRepository extends JpaRepository<Event, BigInteger> {
//    List<Artist> findAllByRegionIdOrderByDateCreatedDesc(BigInteger regionId);

    @Query(value = "SELECT \n" +
            "     pg_terminate_backend(pid)\n" +
            "FROM \n" +
            "    pg_stat_activity \n" +
            "WHERE \n" +
            "    -- don't kill my own connection!\n" +
            "    pid <> pg_backend_pid()\n" +
            "    -- don't kill the connections to other databases\n" +
            "    AND datname = 'dc4blp78tk676'    \n" +
            "    and backend_start < NOW()    - cast('1 minute' as interval)",
            nativeQuery = true)
    void resetConnect();
}
