package edu.berkeley.ground.dao.models.postgres;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import edu.berkeley.ground.dao.PostgresTest;
import edu.berkeley.ground.model.models.Edge;
import edu.berkeley.ground.model.models.Tag;
import edu.berkeley.ground.exceptions.GroundException;

import static org.junit.Assert.*;

public class PostgresEdgeFactoryTest extends PostgresTest {

  public PostgresEdgeFactoryTest() throws GroundException {
    super();
  }

  @Test
  public void testEdgeCreation() throws GroundException {
    String testName = "test";
    String sourceKey = "testKey";

    Map<String, Tag> tagsMap = new HashMap<>();

    PostgresNodeFactory nodeFactory = (PostgresNodeFactory) super.factories.getNodeFactory();
    long fromNodeId = nodeFactory.create("testNode1", null, tagsMap).getId();
    long toNodeId = nodeFactory.create("testNode2", null, tagsMap).getId();

    PostgresEdgeFactory edgeFactory = (PostgresEdgeFactory) super.factories.getEdgeFactory();
    edgeFactory.create(testName, sourceKey, fromNodeId, toNodeId, new HashMap<>());

    Edge edge = edgeFactory.retrieveFromDatabase(testName);

    assertEquals(testName, edge.getName());
    assertEquals(fromNodeId, edge.getFromNodeId());
    assertEquals(toNodeId, edge.getToNodeId());
    assertEquals(sourceKey, edge.getSourceKey());
  }
}
