<template>
  <div style="padding: 10px">
    <div class="container">
      </div>
  </div>
</template>

<script>
// @ is an alias to /src
import * as d3 from "d3";
import request from "@/utils/request";

export default {
  name: 'GraphView',
  components: {

  },
  data() {
    return {
      unionFind: [],
      graphData : {
        nodes:[],
        links:[]
      },
      testData: {
        nodes : [
          {id: "1",
          group: 1},
          {id: "2",
            group: 3},
          {id: "3",
            group: 2},
          {id: "4",
            group: 1},
          {id: "5",
            group: 2},
          {id: "6",
            group: 1},
          {id: "7",
            group: 2},
          {id: "8",
            group: 1},
          {id: "9",
            group: 2},
          {id: "10",
            group: 11}
        ],
        links : [
          {
            source: "1",
            target: "3",
            value: 10
          }
        ]
      },
    }
  },methods : {
    load() {
      let nodeSet = new Set()
      let graphData = {
        nodes:[],
        links:[]
      }
      request.get("api/codes").then(
          res1 => {
            request.get("api/unionfind")
                .then(res2 => {
                      this.unionFind = res2.parent
                  for (let entry of res1) {
                    if(!nodeSet.has(entry.code1.id)) {
                      nodeSet.add(entry.code1.id)
                      graphData.nodes.push(
                          {
                            id : entry.code1.id+"",
                            group: this.unionFind[entry.code1.id]
                          }
                      )
                    }
                    if(!nodeSet.has(entry.code2.id)) {
                      nodeSet.add(entry.code2.id)
                      graphData.nodes.push(
                          {
                            id : entry.code2.id+"",
                            group: this.unionFind[entry.code2.id]
                          }
                      )
                    }
                    if(entry.result === 'EQUAL' || entry.result === 'SAME')
                      graphData.links.push(
                          {
                            source: entry.code1.id+"",
                            target: entry.code2.id+"",
                            value: 10
                          }
                      );
                  }
                  this.graphData = graphData
                  this.initGraph(
                      this.graphData,
                      {
                        nodeId: d => d.id,
                        nodeGroup: d => d.group,
                        nodeTitle: d => `${d.id}\n${d.group}`,
                        linkStrokeWidth: l => Math.sqrt(l.value),
                        width: 1300,
                        height: 660,
                        // invalidation
                      }
                  )
                    }
                );


          }
      );
      return graphData
    },
    initGraph({
                nodes, // an iterable of node objects (typically [{id}, …])
                links // an iterable of link objects (typically [{source, target}, …])
              }, {
                nodeId = d => d.id, // given d in nodes, returns a unique identifier (string)
                nodeGroup, // given d in nodes, returns an (ordinal) value for color
                nodeGroups, // an array of ordinal values representing the node groups
                nodeTitle, // given d in nodes, a title string
                nodeFill = "currentColor", // node stroke fill (if not using a group color encoding)
                nodeStroke = "#fff", // node stroke color
                nodeStrokeWidth = 1.5, // node stroke width, in pixels
                nodeStrokeOpacity = 1, // node stroke opacity
                nodeRadius = 15, // node radius, in pixels
                nodeStrength,
                linkSource = ({source}) => source, // given d in links, returns a node identifier string
                linkTarget = ({target}) => target, // given d in links, returns a node identifier string
                linkStroke = "#999", // link stroke color
                linkStrokeOpacity = 0.6, // link stroke opacity
                linkStrokeWidth = 1.5, // given d in links, returns a stroke width in pixels
                linkStrokeLinecap = "round", // link stroke linecap
                linkStrength,
                colors = d3.schemeTableau10, // an array of color strings, for the node groups
                width = 640, // outer width, in pixels
                height = 400, // outer height, in pixels
                // invalidation // when this promise resolves, stop the simulation
              } = {})
    {
      const N = d3.map(nodes, nodeId).map(intern);
      const LS = d3.map(links, linkSource).map(intern);
      const LT = d3.map(links, linkTarget).map(intern);
      if (nodeTitle === undefined) nodeTitle = (_, i) => N[i];
      const T = nodeTitle == null ? null : d3.map(nodes, nodeTitle);
      const G = nodeGroup == null ? null : d3.map(nodes, nodeGroup).map(intern);
      const W = typeof linkStrokeWidth !== "function" ? null : d3.map(links, linkStrokeWidth);
      const L = typeof linkStroke !== "function" ? null : d3.map(links, linkStroke);

      // Replace the input nodes and links with mutable objects for the simulation.
      nodes = d3.map(nodes, (_, i) => ({id: N[i]}));
      links = d3.map(links, (_, i) => ({source: LS[i], target: LT[i]}));

      // Compute default domains.
      if (G && nodeGroups === undefined) nodeGroups = d3.sort(G);

      // Construct the scales.
      const color = nodeGroup == null ? null : d3.scaleOrdinal(nodeGroups, colors);

      // Construct the forces.
      const forceNode = d3.forceManyBody();
      const forceLink = d3.forceLink(links).id(({index: i}) => N[i]);
      if (nodeStrength !== undefined) forceNode.strength(nodeStrength);
      if (linkStrength !== undefined) forceLink.strength(linkStrength);

      const simulation = d3.forceSimulation(nodes)
          .force("link", forceLink.distance([150]))
          .force("charge", forceNode)
          .force("center",  d3.forceCenter())
          .force("collision", d3.forceCollide().radius(d=>d.radius))
          .on("tick", ticked);

      const svg = d3.select(".container")
          .append("svg")
          .attr("width", width)
          .attr("height", height)
          .attr("viewBox", [-width / 2, -height / 2, width, height])
          .attr("style", "max-width: 100%; height: auto; height: intrinsic;");



      const link = svg.append("g")
          .attr("stroke", typeof linkStroke !== "function" ? linkStroke : null)
          .attr("stroke-opacity", linkStrokeOpacity)
          .attr("stroke-width", typeof linkStrokeWidth !== "function" ? linkStrokeWidth : null)
          .attr("stroke-linecap", linkStrokeLinecap)
          .selectAll("line")
          .data(links)
          .join("line");

      const node = svg.append("g")
          .attr("fill", nodeFill)
          .attr("stroke", nodeStroke)
          .attr("stroke-opacity", nodeStrokeOpacity)
          .attr("stroke-width", nodeStrokeWidth)
          .selectAll("circle")
          .data(nodes)
          .join("circle")
          .attr("r", nodeRadius)
          .call(drag(simulation));

      const text = svg.append("g")
          .attr("class", "text")
          .selectAll("text")
          .data(nodes)
          .enter().append("text")
          .text(d => d.id)

      if (W) link.attr("stroke-width", ({index: i}) => W[i]);
      if (L) link.attr("stroke", ({index: i}) => L[i]);
      if (G) node.attr("fill", ({index: i}) => color(G[i]));
      if (T) node.append("title").text(({index: i}) => T[i]);
      // if (invalidation != null) invalidation.then(() => simulation.stop());

      function intern(value) {
        return value !== null && typeof value === "object" ? value.valueOf() : value;
      }

      function ticked() {
        link
            .attr("x1", d => d.source.x)
            .attr("y1", d => d.source.y)
            .attr("x2", d => d.target.x)
            .attr("y2", d => d.target.y);

        node
            .attr("cx", d => d.x)
            .attr("cy", d => d.y);
        text.attr("x", d => d.x - 5)
            .attr("y", d => d.y + 5);
      }

      function drag(simulation) {
        function dragstarted(event) {
          if (!event.active) simulation.alphaTarget(0.3).restart();
          event.subject.fx = event.subject.x;
          event.subject.fy = event.subject.y;
        }

        function dragged(event) {
          event.subject.fx = event.x;
          event.subject.fy = event.y;
        }

        function dragended(event) {
          if (!event.active) simulation.alphaTarget(0);
          event.subject.fx = null;
          event.subject.fy = null;
        }

        return d3.drag()
            .on("start", dragstarted)
            .on("drag", dragged)
            .on("end", dragended);
      }
    }
  },created() {
    this.load()
  },
    mounted() {
    }
}
</script>
