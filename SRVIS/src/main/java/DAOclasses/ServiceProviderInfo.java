package DAOclasses;

public class ServiceProviderInfo
{
    private int id;
    private String name;
    private String address;
    private String contact;
    private String jobType;
    private String categoryID;
    private String hourlyRate;
    private String experience;
    private String certification;
    private String rating;
    private String serviceCategory;

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getAddress()
    {
        return address;
    }

    public void setAddress(String address)
    {
        this.address = address;
    }

    public String getContact()
    {
        return contact;
    }

    public void setContact(String contact)
    {
        this.contact = contact;
    }

    public String getJobType()
    {
        return jobType;
    }

    public void setJobType(String jobType)
    {
        this.jobType = jobType;
    }

    public String getCategoryID()
    {
        return categoryID;
    }

    public void setCategoryID(String categoryID)
    {
        this.categoryID = categoryID;
    }

    public String getHourlyRate()
    {
        return hourlyRate;
    }

    public void setHourlyRate(String hourlyRate)
    {
        this.hourlyRate = hourlyRate;
    }

    public String getExperience()
    {
        return experience;
    }

    public void setExperience(String experience)
    {
        this.experience = experience;
    }

    public String getCertification()
    {
        return certification;
    }

    public void setCertification(String certification)
    {
        this.certification = certification;
    }

    public String getRating()
    {
        return rating;
    }

    public void setRating(String rating)
    {
        this.rating = rating;
    }


    public String getServiceCategory() {
        return serviceCategory;
    }

    public void setServiceCategory(String serviceCategory) {
        this.serviceCategory = serviceCategory;
    }
}
