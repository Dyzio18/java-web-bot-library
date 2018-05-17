package stdBot;


public class App 
{
    public static void main( String[] args )
    {
        RemoteMapper remoteMapper = new RemoteMapper();

        remoteMapper.start("http://www.wggios.agh.edu.pl/", CollectorTypeEnum.Map, 1);

        SiteMap siteMap = remoteMapper.getResult();

        siteMap.print();
    }
}
