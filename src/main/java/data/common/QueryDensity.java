package data.common;

/**
 * Created by liudongdong on 2015/4/29.
 */
public enum QueryDensity {
    Normal(0),
    Hour(1),
    Day(2),
    Week(3),
    Month(4),
    Year(5)
    ;



    private int density;
    private QueryDensity(int d)
    {
        this.density=d;
    }

    public int toInt()
    {return density;}

    public static QueryDensity valueOf(int density)
    {
        switch (density)
        {
            case 0:
                return QueryDensity.Normal;
            case 1:
                return QueryDensity.Hour;
            case 2:
                return QueryDensity.Day;
            case 3:
                return QueryDensity.Week;
            case 4:
                return QueryDensity.Month;
            case 5:
                return QueryDensity.Year;
            default:
                return QueryDensity.Normal;
        }
    }

    public String getGroupTimeField()
    {
        switch (this)
        {
            case Hour:
                return "hour";
            case Week:
                return "week";
            case Month:
                return "month";
            case Year:
                return "year";
            default:
                return null;
        }
    }


}
