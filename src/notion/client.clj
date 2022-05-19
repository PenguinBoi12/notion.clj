(ns notion.client
  (:require
    [clojure.edn :as edn]
    [notion.api :as api]
    [notion.types.client :refer :all])
  (:import [java.io IOException]))

(defonce ^:private default-config-path "resources/config.edn")
; We might need to handle path like search diffrently to avoid using it with get
; Maybe limiting some route to specefic method (like get, put, ...) and throw
; an error if the method is not allowed
;
; :search {:path "/search", :methods [:post]}
;
(defonce ^:private routes {:user "/users"
                           :page "/pages"
                           :database "/databases"
                           :block "/blocks"
                           :search "/search"})

(defn load-config [filename]
  (try
    (edn/read-string (slurp filename))
    (catch IOException e
      (throw (ex-info "Can't load the configuration!" {:file filename})))))

(defn init! [& config-path]
  (let [config (load-config default-config-path)]
    (->Client config)))

(defn fetch
  ([model client]
    (:results (api/send-request :get (get routes model) client)))
  ([model client id]
    (let [path (str (get routes model) "/" id)]
      (api/send-request :get path client))))

(defn search
  ([client query options]
    (api/send-request :post "/search" client (merge {:query query} options)))
  ([client query]
    (search client query {})))


; (defn make-record [object client]
;   (if (some? object)
;     (into object {:client client})))
;
; (defn users [client]
;   (let [users (get-users client)]
;     (make-record users client)))
;
; (defn user [client id]
;   (let [user (get-user client id)]
;     (make-record user client)))
