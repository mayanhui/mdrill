/**
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.solr.analysis;

import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.position.PositionFilter;

import java.util.Map;

/**
 * Factory for {@link PositionFilter}.
 * Set the positionIncrement of all tokens to the "positionIncrement", except the first return token which retains its
 * original positionIncrement value. The default positionIncrement value is zero.
 * <pre class="prettyprint" >
 * &lt;fieldType name="text_position" class="solr.TextField" positionIncrementGap="100"&gt;
 *   &lt;analyzer&gt;
 *     &lt;tokenizer class="solr.WhitespaceTokenizerFactory"/&gt;
 *     &lt;filter class="solr.PositionFilterFactory" positionIncrement="0"/&gt;
 *   &lt;/analyzer&gt;
 * &lt;/fieldType&gt;</pre>
 *
 * @version $Id: PositionFilterFactory.java 1074023 2011-02-24 02:25:08Z koji $
 * @see org.apache.lucene.analysis.position.PositionFilter
 * @since solr 1.4
 */
public class PositionFilterFactory extends BaseTokenFilterFactory {
  private int positionIncrement;

  @Override
  public void init(Map<String, String> args) {
    super.init(args);
    positionIncrement = getInt("positionIncrement", 0);
  }

  public PositionFilter create(TokenStream input) {
    return new PositionFilter(input, positionIncrement);
  }
}

