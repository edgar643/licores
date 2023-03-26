package Model;

/*
  This Class Copy the Json Structure and get all data into it.
 */
public class DTO_Drinks {

    private DTO_Alcohol[] alcohols;
    private DTO_Type[] types;
    private DTO_Mixer[] mixers;

    public DTO_Alcohol[] getAlcohols() {
        return alcohols;
    }

     public DTO_Type[] getTypes() {
        return types;
    }

    public DTO_Mixer[] getMixers() {
        return mixers;
    }

    public String getMixerNameById(int id) {
        for(DTO_Mixer mixer : getMixers()){
            if( id == mixer.getId()){
                return mixer.getName();
            }
        }
        return null;
    }

         public String getTypeNameById(int id) {
        for(DTO_Type type : getTypes()){
            if( id == type.getId()){
                return type.getName();
            }
        }
        return null;
    }





}