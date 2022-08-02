package lt.bit.zmones;

import javax.servlet.http.HttpServletRequest;

public class SaugumoPatikrinimas {
    private String[] blogiZodziai = {"sql", "insert", "update", "select", "where", "passwd"};
    private String operacija;
    private HttpServletRequest request;

    /**
     * Inicijuoja saugumo patikrinima
     *
     * @param operacija - kokia operacija atliekama ["idpatikrinimas","zmogusadd","zmogusdelte" ir t.t.]
     * @param request   - HTTP requestas
     */

    public SaugumoPatikrinimas(String operacija, HttpServletRequest request) {
        this.operacija = operacija;
        this.request = request;
    }

    /**
     * Grazina saugumo patikrinimo atsakyma true/false
     *
     * @return
     */

    public boolean Atsakymas() {
        switch (this.operacija) {
            case "idpatikrinimas":
                if (request.getParameter("id") != null) {
                    if (arRealusZmogausID(request.getParameter("id"))) {
                        return true;
                    } else {
                        System.out.println("Patikrinimo klaida " + this.operacija);
                        return false;
                    }
                } else {
                    System.out.println("Patikrinimo klaida " + this.operacija);
                    return false;
                }
            case "zmogusadd":
                if (request.getParameter("vardas") != null &&
                        request.getParameter("pavarde") != null) {
                    if (arPrivalomasStringParametrasGeras(request.getParameter("vardas")) &&
                            arPrivalomasStringParametrasGeras(request.getParameter("pavarde"))) {
                        if (request.getParameter("gdata") != null) {
                            if (!arNeprivalomasStringParametrasGeras(request.getParameter("gdata"))) {
                                System.out.println("Ptikrinimo klaida gdata " + this.operacija);
                                return false;
                            }
                        }
                        if (request.getParameter("alga") != null) {
                            if (!arNeprivalomasStringParametrasGeras(request.getParameter("alga"))) {
                                System.out.println("Ptikrinimo klaida alga " + this.operacija);
                                return false;
                            }
                        }
                        return true;
                    } else {
                        System.out.println("Ptikrinimo klaida parametras" + this.operacija);
                        return false;
                    }
                } else {
                    System.out.println("Ptikrinimo klaida null" + this.operacija);
                    return false;
                }
            case "zmogusedit":
                if (request.getParameter("id") != null &&
                        request.getParameter("vardas") != null &&
                        request.getParameter("pavarde") != null) {
                    if (arRealusZmogausID(request.getParameter("id")) &&
                            arPrivalomasStringParametrasGeras(request.getParameter("vardas")) &&
                            arPrivalomasStringParametrasGeras(request.getParameter("pavarde"))) {
                        if (request.getParameter("gdata") != null) {
                            if (!arNeprivalomasStringParametrasGeras(request.getParameter("gdata"))) {
                                System.out.println("Ptikrinimo klaida gdata " + this.operacija);
                                return false;
                            }
                        }
                        if (request.getParameter("alga") != null) {
                            if (!arNeprivalomasStringParametrasGeras(request.getParameter("alga"))) {
                                System.out.println("Ptikrinimo klaida alga " + this.operacija);
                                return false;
                            }
                        }
                        return true;
                    } else {
                        System.out.println("Ptikrinimo klaida parametras" + this.operacija);
                        return false;
                    }
                } else {
                    System.out.println("Ptikrinimo klaida null" + this.operacija);
                    return false;
                }
            case "zmogusdelete":
                if (request.getParameter("id") != null) {
                    if (arRealusZmogausID(request.getParameter("id"))) {
                        return true;
                    } else {
                        System.out.println("Ptikrinimo klaida " + this.operacija);
                        return false;
                    }
                } else {
                    System.out.println("Ptikrinimo klaida " + this.operacija);
                    return false;
                }
            case "kontaktasadd":
                if (request.getParameter("zmogaus_id") != null &&
                        request.getParameter("tipas") != null &&
                        request.getParameter("reiksme") != null) {
                    if (arRealusZmogausID(request.getParameter("zmogaus_id")) &&
                            arPrivalomasStringParametrasGeras(request.getParameter("tipas")) &&
                            arPrivalomasStringParametrasGeras(request.getParameter("reiksme"))) {
                        return true;
                    } else {
                        System.out.println("Ptikrinimo klaida 1" + this.operacija);
                        return false;
                    }
                } else {
                    System.out.println("Ptikrinimo klaida 2" + this.operacija);
                    return false;
                }
            case "kontaktasedit":
                if (request.getParameter("kid") != null &&
                        request.getParameter("tipas") != null &&
                        request.getParameter("reiksme") != null) {
                    if (arRealusKontaktoID(request.getParameter("kid")) &&
                            arPrivalomasStringParametrasGeras(request.getParameter("tipas")) &&
                            arPrivalomasStringParametrasGeras(request.getParameter("reiksme"))) {
                        return true;
                    } else {
                        System.out.println("Ptikrinimo klaida 1" + this.operacija);
                        return false;
                    }
                } else {
                    System.out.println("Ptikrinimo klaida 2" + this.operacija);
                    return false;
                }
            case "kontaktasdelete":
                if (request.getParameter("kid") != null) {
                    if (arRealusKontaktoID(request.getParameter("kid"))) {
                        return true;
                    } else {
                        System.out.println("Ptikrinimo klaida 1" + this.operacija);
                        return false;
                    }
                } else {
                    System.out.println("Ptikrinimo klaida 2" + this.operacija);
                    return false;
                }
            case "adresasadd":
                if (request.getParameter("id") != null &&
                        request.getParameter("valstybe") != null &&
                        request.getParameter("miestas") != null &&
                        request.getParameter("adresas") != null &&
                        request.getParameter("pastokodas") != null) {
                    if (arRealusZmogausID(request.getParameter("id")) &&
                            arPrivalomasStringParametrasGeras(request.getParameter("valstybe")) &&
                            arPrivalomasStringParametrasGeras(request.getParameter("miestas")) &&
                            arPrivalomasStringParametrasGeras(request.getParameter("adresas")) &&
                            arPrivalomasStringParametrasGeras(request.getParameter("pastokodas"))) {
                        return true;
                    } else {
                        System.out.println("Ptikrinimo klaida " + this.operacija);
                        return false;
                    }
                } else {
                    System.out.println("Ptikrinimo klaida " + this.operacija);
                    return false;
                }
            case "adresasedit":
                if (request.getParameter("id") != null &&
                        request.getParameter("aid") != null &&
                        request.getParameter("valstybe") != null &&
                        request.getParameter("miestas") != null &&
                        request.getParameter("adresas") != null &&
                        request.getParameter("pastokodas") != null) {
                    if (arRealusZmogausID(request.getParameter("id")) &&
                            arTeigiamasSkaicius(request.getParameter("aid")) &&
                            arPrivalomasStringParametrasGeras(request.getParameter("valstybe")) &&
                            arPrivalomasStringParametrasGeras(request.getParameter("miestas")) &&
                            arPrivalomasStringParametrasGeras(request.getParameter("adresas")) &&
                            arPrivalomasStringParametrasGeras(request.getParameter("pastokodas"))) {
                        return true;
                    } else {
                        System.out.println("Ptikrinimo klaida " + this.operacija);
                        return false;
                    }
                } else {
                    System.out.println("Ptikrinimo klaida " + this.operacija);
                    return false;
                }
            case "adresasdelete":
                if (request.getParameter("id") != null &&
                        request.getParameter("aid") != null) {
                    if (arRealusZmogausID(request.getParameter("id")) &&
                            arTeigiamasSkaicius(request.getParameter("aid"))) {
                        return true;
                    } else {
                        System.out.println("Ptikrinimo klaida " + this.operacija);
                        return false;
                    }
                } else {
                    System.out.println("Ptikrinimo klaida " + this.operacija);
                    return false;
                }
            default:
                System.out.println("Ptikrinimas pabaigtas");
        }
        System.out.println("Ptikrinimo klaida nezinoma operacija " + this.operacija);
        return false;
    }

    //////////////////////////////////  Patikrinimai
    private boolean arRealusZmogausID(String ids) {
        int id;
        try {
            id = Integer.parseInt(ids);
        } catch (NumberFormatException nfe) {
            System.out.println(nfe.getMessage());
            return false;
        }
        if (id >= 1) {
            Zmogus z = ZmogusRepo.getById(id);
            if (z != null) {
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

    private boolean arRealusKontaktoID(String ids) {
        int id;
        try {
            id = Integer.parseInt(ids);
        } catch (NumberFormatException nfe) {
            System.out.println(nfe.getMessage());
            return false;
        }
        if (id >= 1) {
            Kontaktas k = KontaktasRepo.getById(id);
            if (k != null) {
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

    private boolean arRealusAdresoID(String ids) {
        int id;
        try {
            id = Integer.parseInt(ids);
        } catch (NumberFormatException nfe) {
            System.out.println(nfe.getMessage());
            return false;
        }
        if (id >= 1) {
            Adresas a = AdresasRepo.getById(id);
            if (a != null) {
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

    /**
     * Tikrina ar butinas String equest parametras yra ne null, "" ir sql ir panasiu zodziu.
     *
     * @param s
     * @return
     */

    private boolean arPrivalomasStringParametrasGeras(String s) {
        if (s != null && !"".equals(s)) {
            // Patikrinam ar neturi injectu
            if (arYraInjection(s)) {
                return false;
            }
            return true;
        }
        return false;
    }

    /**
     * Tikrina ar neprivalomas String request parametras neturi sql ir panasiu zodziu.
     *
     * @param s
     * @return
     */


    private boolean arNeprivalomasStringParametrasGeras(String s) {
        if (!"".equals(s)) {
            if (arYraInjection(s)) {
                return false;
            }
            return true;
        }
        return true;
    }

    /**
     * Tikrina ar nera Stringe injectionu
     *
     * @param s
     * @return
     */

    private boolean arYraInjection(String s) {
        for (String word : this.blogiZodziai) {
            if (s.toLowerCase().contains(word)) {
                System.out.println("Rastas piktybinis parametras: " + s);
                return true;
            }
        }
        return false;
    }

    private boolean arTeigiamasSkaicius(String ids) {
        int id;
        try {
            id = Integer.parseInt(ids);
            if (id > 0) {
                return true;
            }
        } catch (NumberFormatException nfe) {
            System.out.println(nfe.getMessage());
            return false;
        }
        return false;
    }

}
