package v1.localhost.drwho.utils;

/**
 * Created by felipe on 26/11/17.
 */
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;


public class MaskUtils {
    public enum  MaskType {
        CPF,
        CNPJ,
        CRM,
        PHONE
    }
    private static final String CPFMask = "###.###.###-##";
    private static final String CNPJMask = "##.###.###/####-##";
    private static final String CRMMask = "########-#";
    private static final String PHONEMask = "(##) #####-####";

    public static String unmask(String s) {
        return s.replaceAll("[^0-9]*", "");
    }

    private static String getDefaultMask(String str) {
        String defaultMask = CPFMask;
        if (str.length() == 14){
            defaultMask = CNPJMask;
        }if(str.length() == 9){
            defaultMask = CRMMask;
        }if(str.length() == 11){
            defaultMask = PHONEMask;
        }
        return CPFMask;
    }

    public static TextWatcher insert(final EditText editText, final MaskType maskType) {
        return new TextWatcher() {

            boolean isUpdating;
            String oldValue = "";

            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String value = MaskUtils.unmask(s.toString());
                String mask;
                switch (maskType) {
                    case CPF:
                        mask = CPFMask;
                        break;
                    case CNPJ:
                        mask = CNPJMask;
                        break;
                    case CRM:
                        mask = CRMMask;
                        break;
                    case PHONE:
                        mask = PHONEMask;
                        break;
                    default:
                        mask = getDefaultMask(value);
                        break;
                }

                String maskAux = "";
                if (isUpdating) {
                    oldValue = value;
                    isUpdating = false;
                    return;
                }
                int i = 0;
                for (char m : mask.toCharArray()) {
                    if ((m != '#' && value.length() > oldValue.length()) || (m != '#' && value.length() < oldValue.length() && value.length() != i)) {
                        maskAux += m;
                        continue;
                    }

                    try {
                        maskAux += value.charAt(i);
                    } catch (Exception e) {
                        break;
                    }
                    i++;
                }
                isUpdating = true;
                editText.setText(maskAux);
                editText.setSelection(maskAux.length());
            }

            public void beforeTextChanged(CharSequence s, int start, int count,int after) {}
            public void afterTextChanged(Editable s) {}
        };
    }
}