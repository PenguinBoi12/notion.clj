(ns notion.client
  (:require
    [clojure.edn :as edn]
    [notion.api.utils :as api]
    [notion.types.user :refer [build-user]]
    [notion.types.page :refer [build-page]]
    [notion.types.database :refer [build-database]]
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

(defn user [client id]
  (build-user (api/fetch client :user id)))

(defn users [client]
  (map build-user (api/fetch client :user)))

(defn database [client id]
  (build-database (api/fetch client :database id)))

(defn page [client id]
  (build-page (api/fetch client :page id)))
