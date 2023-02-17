package me.reclaite.bananosbackend.repository;

import me.reclaite.bananosbackend.model.company.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Integer> {

    @Query("SELECT comp FROM Company comp WHERE comp.id = :id")
    Company getCompanyById(@Param("id") int id);

}
