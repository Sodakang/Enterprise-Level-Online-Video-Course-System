Tool = {
    /**
     * Check for null: if null or "", return true.
     */
    isEmpty: function (obj) {
        if ((typeof obj == 'string')) {
            return !obj || obj.replace(/\s+/g, "") === ""
        } else {
            return (!obj || JSON.stringify(obj) === "{}" || obj.length === 0);
        }
    },

    /**
     * Check for not null.
     */
    isNotEmpty: function (obj) {
        return !this.isEmpty(obj);
    },

    /**
     * Check for length.
     */
    isLength: function (str, min, max) {
        return $.trim(str).length >= min && $.trim(str).length <= max;
    }
};