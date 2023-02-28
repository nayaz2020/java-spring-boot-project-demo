package edu.greenriver.sdiv.myspringproject.service;

import edu.greenriver.sdiv.myspringproject.dbs.IResumeRepository;
import edu.greenriver.sdiv.myspringproject.models.ResumeData;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.NoSuchElementException;

/**
 * @author nemat
 * @version 1.2
 */
@Service
public class ResumeService
{
    private IResumeRepository repo;

    /**
     * @param repo which is a spring bean will be passed in
     */
    public ResumeService(IResumeRepository repo)
    {
        this.repo = repo;
    }

    /**
     * @return all data from the model class
     */
    public List<ResumeData> allData()
    {
        return repo.findAll();
    }


    /**
     * @param id of the record to search for
     * @return true if data exists otherwise null
     */
    public boolean dataExist(int id)
    {
        return byId(id) != null;
    }

    /**
     * @param id of the record to search for
     * @return record from the repo
     */
    public ResumeData byId(int id)
    {
        return repo.findById(id).orElse(null);
    }


    /**
     * @param newData the bounded data to be saved
     * @return saved data
     */
    public ResumeData saveData(ResumeData newData)
    {
        return repo.save(newData);
    }

    /**
     * @param data to be edited
     * @return edited version of the record
     * 
     */
    public ResumeData editData(ResumeData data)
    {
        if(repo.findById(data.getId()).isEmpty())
        {
            throw new NoSuchElementException("Missing Data...");
        }
        return repo.save(data);
    }

    /**
     * @param id id of the record to be deleted
     */
    public void deleteRecord(int id)
    {
        repo.deleteById(id);
    }

    @Override
    public String toString() {
        return "ResumeService{" +
                "repo=" + repo +
                '}';
    }
}