(ns ^{:doc "Client configs functions"
      :author "Simon Roy"}
  notion.config
  (:require [clojure.edn :as edn])
  (:import [java.io IOException]))

(defn load-config
  "Reads and returns the content of the given edn file"
  [filename]
  (try
    (edn/read-string (slurp filename))
    (catch IOException e
      (throw (ex-info "Can't load the configuration!" {:file filename})))))

(def load-default-config
  (partial load-config "resources/config.edn"))
