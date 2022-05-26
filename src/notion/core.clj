(ns notion.core
  (:require
    [clojure.edn :as edn]
    [notion.client :as client])
  (:gen-class))

(def client (client/init!))

(defn -main [& args]
  (let [db (client/database client "72fed03b27ca48278f41000c4920afe9")]
    (println (client/parent client db) )))
(-main)
