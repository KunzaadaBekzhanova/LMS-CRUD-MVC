package hola.service;

import hola.models.Company;
import hola.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CompanyService {

    private CompanyRepository companyRepository;

    @Autowired
    public CompanyService(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }


    public void save(Company company) {
        companyRepository.save(company);
    }


    public List<Company> getCompanies() {
        return companyRepository.getCompanies();
    }


    public Company getCompanyById(Long id) {
        return companyRepository.getCompanyById(id);
    }


    public void deleteCompany(Long id) {
        companyRepository.deleteCompany(id);
    }


    public void updateCompany(Long id,Company updatedCompany) {
        companyRepository.updateCompany(id,updatedCompany);
    }
}

