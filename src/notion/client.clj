(ns notion.client
  (:require
    [notion.types.client :as client]
    [notion.api.user :refer :all])
  (:import [java.io IOException]))

(defonce ^:private default-config-path "resources/config.edn")

(defn load-config [filename]
  (try
    (edn/read-string (slurp filename))
    (catch IOException e
      (throw (ex-info "Can't load the configuration!" {:file filename})))))

(defn init! [& config-path]
  (let [config (load-config default-config-path)]
    (client/->Client config)))
