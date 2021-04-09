package customer;

public enum EnumServiceCategory {
    Electrician(1),
    Plumber(2),
    Carpenter(3),
    Painter(4),
    Cleaner(5);

    int categoryNumber = 0;

    EnumServiceCategory(int serviceNumber)
    {
        this.categoryNumber = serviceNumber;
    }
}