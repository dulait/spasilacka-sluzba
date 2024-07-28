package sistemska_operacija.export;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import db.DbBroker;
import domen.OpstiDomenskiObjekat;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.reflections.Reflections;
import sistemska_operacija.OpstaSO;
import utils.LocalDateAdapter;

public class SOExportToJson extends OpstaSO {

    private boolean uspeh = false;
    private String jsonFilePath;

    public SOExportToJson() {
    }

    public boolean isUspeh() {
        return uspeh;
    }

    public String getJsonFilePath() {
        return jsonFilePath;
    }

    @Override
    protected void izvrsiSpecificnuOperaciju() {
        try {
            Map<String, List<OpstiDomenskiObjekat>> entitiesMap = new HashMap<>();
            Reflections reflections = new Reflections("domen");
            Set<Class<? extends OpstiDomenskiObjekat>> subTypes = reflections.getSubTypesOf(OpstiDomenskiObjekat.class);

            for (Class<? extends OpstiDomenskiObjekat> subType : subTypes) {
                OpstiDomenskiObjekat entity = subType.getDeclaredConstructor().newInstance();
                List<OpstiDomenskiObjekat> entityList = DbBroker.getInstanca().getAllOpstiDomenskiObjekats(entity);
                entitiesMap.put(subType.getSimpleName(), entityList);
            }

            uspeh = writeJsonToFile(entitiesMap);
        } catch (IllegalAccessException | IllegalArgumentException | InstantiationException | NoSuchMethodException | SecurityException | InvocationTargetException e) {
            uspeh = false;
        }
    }

    private boolean writeJsonToFile(Map<String, List<OpstiDomenskiObjekat>> entitiesMap) {
        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .registerTypeAdapter(LocalDate.class, LocalDateAdapter.serializer)
                .registerTypeAdapter(LocalDate.class, LocalDateAdapter.deserializer)
                .create();
        String json = gson.toJson(entitiesMap);

        String userHome = System.getProperty("user.home");
        File file = new File(userHome + File.separator + "Desktop" + File.separator + "spasilacka-sluzba.json");
        jsonFilePath = file.getAbsolutePath();

        try (FileWriter writer = new FileWriter(file)) {
            writer.write(json);
            return true;
        } catch (IOException e) {
            return false;
        }
    }
}
