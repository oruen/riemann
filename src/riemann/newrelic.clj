(ns riemann.newrelic
  "Publish custom metrics to NewRelic"
  (:import com.newrelic.api.agent.NewRelic)
  (:use riemann.common))

(defn newrelic
  "Creates an adaptor to forward events to newrelic. The newrelic event will
  contain the metric name and value.

  Tested with:
  (streams
    newrelic)"
  [e]
  (let [name (str (:newrelic_name e))
        value (:metric e)]
    (NewRelic/recordMetric name value)))
