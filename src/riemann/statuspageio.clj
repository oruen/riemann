(ns riemann.statuspageio
  "Alert Statuspage.io on service state changes"
  (:require [clj-http.client :as client])
  (:use [clojure.string :only (join)])
  (:use riemann.common))

(defn statuspageio
  [page-id api-key]
    (fn stream [e]
      (let [component-id (str (:component_id e))
        component-status (str (:component_status e))
        value (:metric e)]
      (client/patch (join ["https://api.statuspage.io/v1/pages/" page-id "/components/" component-id ".json"])
        {:content-type :json
         :headers {"Authorization" (join ["OAuth " api-key])}
         :body (join ["{\"component\": {\"status\": \"" component-status "\"}}"])})
      )))
