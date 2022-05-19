(ns notion.types.client)

(defprotocol ClientProtocol
  (token [this]))

(deftype Client [config]
  ClientProtocol
  (token [_] (:token config)))
