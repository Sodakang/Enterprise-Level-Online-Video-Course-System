/**
 * Array filter. For example：{{SECTION_CHARGE | optionKV(section.charge)}}
 * @param object. For example：{CHARGE:{key:"C", value:"Charge"},FREE:{key:"F", value:"Free"}}
 * @param key. For example：C
 * @returns {string} For example：Charge
 */
let optionKV = (object, key) =>  {
    if (!object || !key) {
        return "";
    } else {
        let result = "";
        for(let enums in object){
            if (key === object[enums]["key"]) {
                result = object[enums]["value"];
            }
        }
        return result;
    }
};

export default {
    optionKV,
};