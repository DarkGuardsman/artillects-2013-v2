package dark.drone;

import java.util.ArrayList;
import java.util.List;

import dark.drone.api.IHiveEntity;

public class EntityManager
{
    public static List<IHiveEntity> registryList = new ArrayList<IHiveEntity>();

    public static void registerEntity(IHiveEntity entity)
    {
        if (!registryList.contains(entity))
        {

        }
    }
}
