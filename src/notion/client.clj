(ns notion.client
  (:require
    [clojure.edn :as edn]
    [notion.api :as api]
    [notion.types.client :refer :all])
  (:import [java.io IOException]))

(defonce ^:private default-config-path "resources/config.edn")

(defn load-config [filename]
  (try
    (edn/read-string (slurp filename))
    (catch IOException e
      (throw (ex-info "Can't load the configuration!" {:file filename})))))

(defn init! [& config-path]
  (let [config (load-config default-config-path)]
    (->Client config)))

(defn search
  ([client query options]
    (api/search client query options))
  ([client query]
    (api/search client query {})))

(defn users [client id]
  (api/fetch client :user))

(defn user [client id]
  (api/fetch client :user id))
